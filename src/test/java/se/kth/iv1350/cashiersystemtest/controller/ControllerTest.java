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
        SaleDTO saleDTO = controller.endSale();

        Iterator<ItemInCartDTO> it = saleDTO.getItemsInCartDTO().iterator();

        assertEquals("123", it.next().getItemDTO().id(),
                "Item identifier should match the scanned item");
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

