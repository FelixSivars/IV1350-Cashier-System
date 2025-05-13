package se.kth.iv1350.cashiersystem.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.cashiersystem.controller.Controller;
import se.kth.iv1350.cashiersystem.controller.OperationFailureException;
import se.kth.iv1350.cashiersystem.dto.ItemDTO;
import se.kth.iv1350.cashiersystem.dto.ItemInCartDTO;
import se.kth.iv1350.cashiersystem.integration.*;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class SaleTest {
    private static Sale sale;
    private static ItemDTO itemDTO;
    private static ItemDTO anotherItemDTO;

    @BeforeEach
    public void startNewSale() {
        sale = new Sale();
        
        itemDTO = new ItemDTO.Builder()
                .id("123")
                .name("Bobs Hallonsylt")
                .price(14.90f)
                .description("TASTY")
                .vatPercentage(10)
                .build();

        anotherItemDTO = new ItemDTO.Builder()
                .id("456")
                .name("Felix Ketchup")
                .price(23.49f)
                .description("MUMS")
                .vatPercentage(7)
                .build();
    }

    @AfterEach
    public void tearDown() {
        sale = null;
        itemDTO = null;
        anotherItemDTO = null;
    }

    @Test
    public void testIsItemInSale() {
        sale.addItem(itemDTO.toItem(), 1);
        assertTrue(sale.isItemInSale("123"), "Item should be in sale.");
    }

    @Test
    public void testIsItemNotInSale() {
        assertFalse(sale.isItemInSale("123"), "Item should not be in sale.");
    }

    @Test
    public void testIncreaseQuantity() {
        sale.addItem(itemDTO.toItem(), 1);
        sale.increaseQuantity("123", 2);

        Iterator<ItemInCartDTO> it = sale.toDTO().itemsInCartDTO().iterator();
        assertEquals(3, it.next().quantity(),
                "Quantity should be increased, expected 3.");
    }

    @Test
    public void testAddItem() {
        sale.addItem(itemDTO.toItem(), 1);

        Iterator<ItemInCartDTO> it = sale.toDTO().itemsInCartDTO().iterator();
        assertEquals(itemDTO, it.next().itemDTO(),
                "Item should be added in sale.");
    }

    @Test
    public void testEndSale() {
        sale.addItem(anotherItemDTO.toItem(), 1);
        float totalPrice = sale.endSale();

        assertEquals(23.49f, totalPrice,
                "The total price is not equal to the expected total price.");
    }

    @Test
    public void testGetSaleDTO() {
        sale.addItem(itemDTO.toItem(), 1);

        Iterator<ItemInCartDTO> it = sale.toDTO().itemsInCartDTO().iterator();
        assertEquals(itemDTO.name(), it.next().itemDTO().name(),
                "Item name in DTO should match the added item name.");
    }

    @Test
    public void testUpdateRunningTotal() {
        sale.addItem(itemDTO.toItem(), 1);
        sale.addItem(anotherItemDTO.toItem(), 1);
        assertEquals(itemDTO.price() + anotherItemDTO.price(), sale.getRunningTotal(),
                "The total price should be equal to the items added.");

    }

    @Test
    public void testVatCalculation() throws DatabaseFailureException, OperationFailureException {
        // Create a new controller with a known VAT rate
        RegistryCreator registryCreator = RegistryCreator.getRegistryCreatorInstance();
        PrinterService printer = PrinterService.getPrinterServiceInstance();
        Controller testController = new Controller(registryCreator, printer);

        // Create an item with a known price and VAT rate (25%)
        ItemDTO itemDTO =  new ItemDTO.Builder()
                .id("test")
                .name("Test Item")
                .price(100f)
                .description("Description")
                .vatPercentage(25)
                .build();

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



