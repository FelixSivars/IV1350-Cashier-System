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
        itemDTO = new ItemDTO.Builder()
                .id("123")
                .name("Bobs Hallonsylt")
                .price(14.90f)
                .description("TASTY")
                .vatPercentage(10)
                .build();
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
        assertThrows(InvalidItemIdException.class, () -> inventoryRegistryHandler.fetchItemById("79"),
                "The specified item id is invalid and thus expecting an InvalidItemIdException");
    }

    @Test
    public void testDatabaseFailureException() {
        assertThrows(DatabaseFailureException.class, () -> inventoryRegistryHandler.fetchItemById("ghj789"),
                "The specified item id is hardcoded to throw a DatabaseFailureException");
    }

    @Test
    public void testValidateItemId() {
        assertDoesNotThrow(() -> inventoryRegistryHandler.fetchItemById("123"),
                "The specified item id is valid and should not throw an exception.");
    }

    @Test
    public void testUpdateInventoryRegistry() {
        InventoryRegistryHandler inventoryHandler = new InventoryRegistryHandler();

        // Create a test SaleDTO
        Collection<ItemInCartDTO> items = new ArrayList<>();

        ItemDTO itemDTO =  new ItemDTO.Builder()
                .id("test")
                .name("Test Item")
                .price(10f)
                .description("Description")
                .vatPercentage(25)
                .build();

        items.add(new ItemInCartDTO(itemDTO, 2));

        SaleDTO saleDTO = new SaleDTO.Builder()
                .dateTime(LocalDateTime.now())
                .runningTotal(20f)
                .vatTotal(5f)
                .amountPaid(50f)
                .change(30f)
                .itemsInCartDTO(items)
                .build();

        // Verify that no exception is thrown when updating
        assertDoesNotThrow(() -> inventoryHandler.updateInventoryRegistry(saleDTO),
                "Should not throw exception when updating inventory registry");
    }
}

