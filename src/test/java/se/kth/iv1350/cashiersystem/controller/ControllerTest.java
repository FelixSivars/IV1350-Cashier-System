package se.kth.iv1350.cashiersystem.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.cashiersystem.dto.ItemDTO;
import se.kth.iv1350.cashiersystem.dto.ItemInCartDTO;
import se.kth.iv1350.cashiersystem.dto.SaleDTO;
import se.kth.iv1350.cashiersystem.integration.*;

import java.util.Collection;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {
    private static ItemDTO itemDTO;
    private static Controller controller;

    @BeforeAll
    public static void setUp() {
        itemDTO = new ItemDTO("123", "Bobs Hallonsylt", 14.90f, "TASTY", 10);

        RegistryCreator registryCreator = new RegistryCreator();
        PrinterService printer = new PrinterService();
        controller = new Controller(registryCreator, printer);
        controller.setInventoryRegistryHandler(new InventoryRegistryHandler(itemDTO));
    }

    @BeforeEach
    public void startNewSale() {
        controller.startSale();
    }

    @Test
    public void testScanItem() throws DatabaseFailureException, OperationFailureException {
        controller.scanItem("123", 1);
        SaleDTO saleDTO = controller.getSaleDTO();

        Iterator<ItemInCartDTO> it = saleDTO.itemsInCartDTO().iterator();

        assertEquals("123", it.next().itemDTO().id(),
                "Item identifier should match the scanned item");
    }

    @Test
    public void testScanRepeatedItem() throws DatabaseFailureException, OperationFailureException {
        // Scan the same item twice
        controller.scanItem("123", 1);
        controller.scanItem("123", 1);

        SaleDTO saleDTO = controller.getSaleDTO();
        Collection<ItemInCartDTO> itemsInCart = saleDTO.itemsInCartDTO();

        // Should still have only one unique item
        assertEquals(1, itemsInCart.size(), "Should have exactly one unique item in cart");

        // Item quantity should be 2
        ItemInCartDTO itemInCart = itemsInCart.iterator().next();
        assertEquals(2, itemInCart.quantity(), "Item quantity should be 2");
    }

    @Test
    public void testScanInvalidItemException() {
        assertThrows(OperationFailureException.class, () -> controller.scanItem("nonexistent", 1),
                "The specified item id is invalid and thus expecting an OperationFailureException");
    }

    @Disabled
    @Test
        public void testScanInvalidItem() throws DatabaseFailureException, OperationFailureException {
            // Try to scan an item with an ID that doesn't exist in inventory
            controller.scanItem("nonexistent", 1);

            // Get sale information
            SaleDTO saleDTO = controller.getSaleDTO();
            Collection<ItemInCartDTO> itemsInCart = saleDTO.itemsInCartDTO();

            // Verify that no item was added
            assertEquals(0, itemsInCart.size(), "No item should be added when scanning invalid ID");
    }

    @Test
    public void testProcessPayment() throws DatabaseFailureException, OperationFailureException {
        controller.scanItem("123", 1);
        controller.endSale();
        float change = controller.processPayment(100);

        assertTrue(change >= 0,
                "The change should be non negative.");
    }

    @Test
    public void testGetItemDTOFromId() throws DatabaseFailureException, InvalidItemIdException {
        ItemDTO itemDTO = controller.getItemDTOFromId("123");

        assertEquals(ControllerTest.itemDTO, itemDTO, "Item should exist in item catalog.");
    }
}

