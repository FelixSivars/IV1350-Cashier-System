package se.kth.iv1350.cashiersystemtest.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.cashiersystem.dto.ItemDTO;
import se.kth.iv1350.cashiersystem.dto.ItemInCartDTO;
import se.kth.iv1350.cashiersystem.integration.InventoryRegistryHandler;
import se.kth.iv1350.cashiersystem.integration.Printer;
import se.kth.iv1350.cashiersystem.integration.RegistryCreator;
import se.kth.iv1350.cashiersystem.model.Sale;
import se.kth.iv1350.cashiersystem.controller.Controller;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class SaleTest {
    private static Sale sale;
    private static ItemDTO itemDTO1;
    private static ItemDTO itemDTO2;

    @BeforeEach
    public void startNewSale() {
        sale = new Sale();
        itemDTO1 = new ItemDTO("123", "Bobs Hallonsylt", 14.90f, "TASTY", 10);
        itemDTO2 = new ItemDTO("456", "Felix Ketchup", 23.49f, "MUMS", 7);
    }

    @AfterEach
    public void tearDown() {
        sale = null;
        itemDTO1 = null;
        itemDTO2 = null;
    }

    @Test
    public void testIsItemInSale() {
        sale.addItem(itemDTO1.toItem(), 1);
        assertTrue(sale.isItemInSale("123"), "Item should be in sale.");
    }

    @Test
    public void testIsItemNotInSale() {
        assertFalse(sale.isItemInSale("123"), "Item should not be in sale.");
    }

    @Test
    public void testIncreaseQuantity() {
        sale.addItem(itemDTO1.toItem(), 1);
        sale.increaseQuantity("123", 2);

        Iterator<ItemInCartDTO> it = sale.toDTO().getItemsInCartDTO().iterator();
        assertEquals(3, it.next().getQuantity(),
                "Quantity should be increased, expected 3.");
    }

    @Test
    public void testAddItem() {
        sale.addItem(itemDTO1.toItem(), 1);

        Iterator<ItemInCartDTO> it = sale.toDTO().getItemsInCartDTO().iterator();
        assertEquals(itemDTO1, it.next().getItemDTO(),
                "Item should be added in sale.");
    }

    @Test
    public void testEndSale() {
        sale.addItem(itemDTO2.toItem(), 1);
        float totalPrice = sale.endSale().getRunningTotal();

        assertEquals(23.49f, totalPrice,
                "The total price is not equal to the expected total price.");
    }

    @Test
    public void testGetSaleDTO() {
        sale.addItem(itemDTO1.toItem(), 1);

        Iterator<ItemInCartDTO> it = sale.toDTO().getItemsInCartDTO().iterator();
        assertEquals(itemDTO1.getName(), it.next().getItemDTO().getName(),
                "Item name in DTO should match the added item name.");
    }

    @Test
    public void testUpdateRunningTotal() {
        sale.addItem(itemDTO1.toItem(), 1);
        sale.addItem(itemDTO2.toItem(), 1);
        assertEquals(itemDTO1.getPrice() + itemDTO2.getPrice(), sale.getRunningTotal(),
                "The total price should be equal to the items added.");

    }

    @Test
    public void testVatCalculation() {
        // Create a new controller with a known VAT rate
        RegistryCreator registryCreator = new RegistryCreator();
        Printer printer = new Printer();
        Controller testController = new Controller(registryCreator, printer);
        
        // Create an item with a known price and VAT rate (25%)
        ItemDTO itemDTO = new ItemDTO("test", "Test Item", 100.0f, "Description", 25);
        InventoryRegistryHandler inventoryHandler = new InventoryRegistryHandler(itemDTO);
        testController.setInventoryRegistryHandler(inventoryHandler);
        
        // Start a sale and scan the item
        testController.startSale();
        testController.scanItem("test", 1);
        
        // Expected VAT is 25% of 100.0 = 25.0
        float expectedVat = 25.0f;
        assertEquals(expectedVat, testController.getVatTotal(), 0.001f,
                "VAT should be calculated correctly based on the item's VAT rate");
    }
}



