package se.kth.iv1350.cashiersystemtest.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.cashiersystem.controller.Controller;
import se.kth.iv1350.cashiersystem.dto.ItemDTO;
import se.kth.iv1350.cashiersystem.dto.ItemInCartDTO;
import se.kth.iv1350.cashiersystem.dto.SaleDTO;
import se.kth.iv1350.cashiersystem.integration.Printer;
import se.kth.iv1350.cashiersystem.integration.RegistryCreator;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ControllerTest {
    private static RegistryCreator registryCreator;
    private static Printer printer;
    private static Controller controller;

    @BeforeAll
    public static void setUp() {
        registryCreator = new RegistryCreator();
        printer = new Printer();
        controller = new Controller(registryCreator, printer);
    }

    @BeforeEach
    public void startNewSale() {
        controller.startSale();
    }

    @Test
    /** Is dependent on InventoryRegistryHandler hardcoded "ItemCatalog". */
    public void testScanItem() {
        controller.scanItem("abc123", 1);
        SaleDTO saleDTO = controller.endSale();

        Iterator<ItemInCartDTO> it = saleDTO.getItemsInCartDTO().iterator();

        assertEquals("abc123", it.next().getItemDTO().id(),
                "Item identifier should match the scanned item");
    }

    @Test
    /** Is dependent on InventoryRegistryHandler hardcoded "ItemCatalog". */
    public void testProcessPayment() {
        controller.scanItem("abc123", 1);
        controller.endSale();
        float change = controller.processPayment(100);

        assertTrue(change >= 0,
                "The change should be non negative.");
    }

    @Test
    public void testGetItemDTOFromId() {
        ItemDTO itemDTO = controller.getItemDTOFromId("abc123");

        assertEquals(new ItemDTO("abc123", "BigWheel Oatmeal", 29.90f,
                        "BigWheel Oatmeal 500 g, whole grain oats, high fiber, gluten free", 6),
                itemDTO, "Item should exist in item catalog.");
    }
}

