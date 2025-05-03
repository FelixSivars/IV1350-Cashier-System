package se.kth.iv1350.cashiersystemtest.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.cashiersystem.controller.Controller;
import se.kth.iv1350.cashiersystem.dto.ItemDTO;
import se.kth.iv1350.cashiersystem.dto.ItemInCartDTO;
import se.kth.iv1350.cashiersystem.dto.SaleDTO;
import se.kth.iv1350.cashiersystem.integration.InventoryRegistryHandler;
import se.kth.iv1350.cashiersystem.integration.Printer;
import se.kth.iv1350.cashiersystem.integration.RegistryCreator;

import java.util.Collection;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ControllerTest {
    private static ItemDTO itemDTO;
    private static Controller controller;

    @BeforeAll
    public static void setUp() {
        itemDTO = new ItemDTO("123", "Bobs Hallonsylt", 14.90f, "TASTY", 10);

        RegistryCreator registryCreator = new RegistryCreator();
        Printer printer = new Printer();
        controller = new Controller(registryCreator, printer);
        controller.setInventoryRegistryHandler(new InventoryRegistryHandler(itemDTO));
    }

    @BeforeEach
    public void startNewSale() {
        controller.startSale();
    }

    @Test
    public void testScanItem() {
        controller.scanItem("123", 1);
        SaleDTO saleDTO = controller.getSaleDTO();

        Iterator<ItemInCartDTO> it = saleDTO.getItemsInCartDTO().iterator();

        assertEquals("123", it.next().getItemDTO().id(),
                "Item identifier should match the scanned item");
    }

    @Test
    public void testScanRepeatedItem() {
        // Scan the same item twice
        controller.scanItem("123", 1);
        controller.scanItem("123", 1);
        
        SaleDTO saleDTO = controller.getSaleDTO();
        Collection<ItemInCartDTO> itemsInCart = saleDTO.getItemsInCartDTO();
        
        // Should still have only one unique item
        assertEquals(1, itemsInCart.size(), "Should have exactly one unique item in cart");
        
        // Item quantity should be 2
        ItemInCartDTO itemInCart = itemsInCart.iterator().next();
        assertEquals(2, itemInCart.getQuantity(), "Item quantity should be 2");
    }

    @Test
    public void testScanInvalidItem() {
        // Try to scan an item with an ID that doesn't exist in inventory
        controller.scanItem("nonexistent", 1);
        
        // Get sale information
        SaleDTO saleDTO = controller.getSaleDTO();
        Collection<ItemInCartDTO> itemsInCart = saleDTO.getItemsInCartDTO();
        
        // Verify that no item was added
        assertEquals(0, itemsInCart.size(), "No item should be added when scanning invalid ID");
    }

    @Test
    public void testMultipleItemsWithQuantities() {
        controller.scanItem("123", 3);  // Add 3 of item 123
        
        // Set up a second test item in the inventory handler
        ItemDTO secondItem = new ItemDTO("456", "Second Item", 20.0f, "Another item", 12);
        InventoryRegistryHandler inventoryHandler = new InventoryRegistryHandler(secondItem);
        controller.setInventoryRegistryHandler(inventoryHandler);
        
        controller.scanItem("456", 2);  // Add 2 of item 456
        
        // Get sale information
        SaleDTO saleDTO = controller.getSaleDTO();
        Collection<ItemInCartDTO> itemsInCart = saleDTO.getItemsInCartDTO();
        
        assertEquals(2, itemsInCart.size(), "Should have exactly two unique items in cart");
        
        // Check that the running total is calculated correctly
        // First item: 3 * 14.90 = 44.7
        // Second item: 2 * 20.0 = 40.0
        // Total: 84.7
        assertEquals(84.7f, saleDTO.getRunningTotal(), 0.01, 
                "Running total should be sum of all items times their quantities");
    }

    @Test
    public void testProcessPayment() {
        controller.scanItem("123", 1);
        controller.endSale();
        float change = controller.processPayment(100);

        assertTrue(change >= 0,
                "The change should be non negative.");
    }

    @Test
    public void testGetItemDTOFromId() {
        ItemDTO itemDTO = controller.getItemDTOFromId("123");

        assertEquals(ControllerTest.itemDTO, itemDTO, "Item should exist in item catalog.");
    }
}

