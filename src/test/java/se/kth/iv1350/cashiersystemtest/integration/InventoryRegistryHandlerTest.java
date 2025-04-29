package se.kth.iv1350.cashiersystemtest.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.cashiersystem.dto.ItemDTO;
import se.kth.iv1350.cashiersystem.integration.InventoryRegistryHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventoryRegistryHandlerTest {
    private static InventoryRegistryHandler inventoryRegistryHandler;

    @BeforeEach
    public void startNewSale() {
        inventoryRegistryHandler = new InventoryRegistryHandler();
    }

    @AfterEach
    public void tearDown() {
        inventoryRegistryHandler = null;
    }

    @Test
    /** Is dependent on InventoryRegistryHandler hardcoded "ItemCatalog". */
    public void testFetchItemById() {
        ItemDTO itemDTO = inventoryRegistryHandler.fetchItemById("abc123");
        assertEquals("BigWheel Oatmeal", itemDTO.getName(),
                "Fetched itemDTO's name should match the expected");
    }

    @Test
    /** Is dependent on InventoryRegistryHandler hardcoded "ItemCatalog". */
    public void testIsValidItemId() {
        assertEquals(true, inventoryRegistryHandler.isValidItemId("abc123"),
                "ItemID should exist in the hardcoded ItemCatalog.");
    }
}

