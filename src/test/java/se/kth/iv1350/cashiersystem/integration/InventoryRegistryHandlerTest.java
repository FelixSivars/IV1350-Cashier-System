package se.kth.iv1350.cashiersystem.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.cashiersystem.dto.ItemDTO;
import se.kth.iv1350.cashiersystem.dto.ItemInCartDTO;
import se.kth.iv1350.cashiersystem.dto.SaleDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

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
    public void testFetchItemById() throws DatabaseFailureException, InvalidItemIdException {
        ItemDTO itemDTO = inventoryRegistryHandler.fetchItemById("123");
        assertEquals("Bobs Hallonsylt", itemDTO.name(),
                "Fetched itemDTO's name should match the expected");
    }

    @Test
    public void testValidateItemIdException() {
        assertThrows(InvalidItemIdException.class, () -> inventoryRegistryHandler.validateItemId("79"),
                "The specified item id is invalid and thus expecting an InvalidItemIdException");
    }

    @Test
    public void testDatabaseFailureException() {
        assertThrows(DatabaseFailureException.class, () -> inventoryRegistryHandler.validateItemId("ghj789"),
                "The specified item id is hardcoded to throw a DatabaseFailureException");
    }

    @Test
    public void testValidateItemId() {
        assertDoesNotThrow(() -> inventoryRegistryHandler.validateItemId("123"),
                "The specified item id is valid and should not throw an exception.");
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

