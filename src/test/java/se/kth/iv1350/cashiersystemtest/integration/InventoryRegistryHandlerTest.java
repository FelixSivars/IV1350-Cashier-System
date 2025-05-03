package se.kth.iv1350.cashiersystemtest.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.cashiersystem.dto.ItemDTO;
import se.kth.iv1350.cashiersystem.dto.ItemInCartDTO;
import se.kth.iv1350.cashiersystem.dto.SaleDTO;
import se.kth.iv1350.cashiersystem.integration.InventoryRegistryHandler;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

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

    @Test
    public void testUpdateInventoryRegistry() {
        InventoryRegistryHandler inventoryHandler = new InventoryRegistryHandler();
        
        // Create a test SaleDTO
        Collection<ItemInCartDTO> items = new ArrayList<>();
        ItemDTO itemDTO = new ItemDTO("123", "Test Item", 10.0f, "Test Description", 25);
        items.add(new ItemInCartDTO(itemDTO, 2));
        
        SaleDTO saleDTO = new SaleDTO(
                LocalDateTime.now(),
                20.0f,   // runningTotal
                5.0f,    // vatTotal
                50.0f,   // amountPaid
                30.0f,   // change
                0.0f,    // discount
                items    // itemsInCartDTO
        );
        
        // Verify that no exception is thrown when updating
        assertDoesNotThrow(() -> inventoryHandler.updateInventoryRegistry(saleDTO),
                "Should not throw exception when updating inventory registry");
    }
}

