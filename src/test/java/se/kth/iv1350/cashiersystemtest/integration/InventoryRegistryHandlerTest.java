package se.kth.iv1350.cashiersystemtest.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.cashiersystem.dto.ItemDTO;
import se.kth.iv1350.cashiersystem.integration.InventoryRegistryHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InventoryRegistryHandlerTest {
    private static InventoryRegistryHandler inventoryRegistryHandler;
    private static ItemDTO itemDTO;

    @BeforeAll
    public static void setUp() {
        itemDTO = new ItemDTO("123", "Bobs Hallonsylt", 14.90f, "TASTY", 10);
    }

    @BeforeEach
    public void startNewSale() {
        inventoryRegistryHandler = new InventoryRegistryHandler(itemDTO);
    }

    @AfterEach
    public void tearDown() {
        inventoryRegistryHandler = null;
    }

    @Test
    public void testFetchItemById() {
        ItemDTO itemDTO = inventoryRegistryHandler.fetchItemById("123");
        assertEquals("Bobs Hallonsylt", itemDTO.getName(),
                "Fetched itemDTO's name should match the expected");
    }

    @Test
    public void testIsValidItemId() {
        assertTrue(inventoryRegistryHandler.isValidItemId("123"),
                "ItemID should exist in the hardcoded ItemCatalog.");
    }
}

