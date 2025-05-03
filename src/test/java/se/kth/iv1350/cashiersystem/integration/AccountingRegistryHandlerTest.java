package se.kth.iv1350.cashiersystem.integration;

import org.junit.jupiter.api.Test;
import se.kth.iv1350.cashiersystem.dto.ItemDTO;
import se.kth.iv1350.cashiersystem.dto.ItemInCartDTO;
import se.kth.iv1350.cashiersystem.dto.SaleDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class AccountingRegistryHandlerTest {

    @Test
    public void testUpdateAccountingRegistry() {
        AccountingRegistryHandler handler = new AccountingRegistryHandler();

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
        assertDoesNotThrow(() -> handler.updateAccountingRegistry(saleDTO),
                "Should not throw exception when updating accounting registry");
    }
}