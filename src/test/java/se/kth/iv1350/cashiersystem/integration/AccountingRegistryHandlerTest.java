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
        ItemDTO itemDTO = new ItemDTO.Builder()
                .id("123")
                .name("Bobs Hallonsylt")
                .price(14.90f)
                .description("TASTY")
                .vatPercentage(10)
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
        assertDoesNotThrow(() -> handler.updateAccountingRegistry(saleDTO),
                "Should not throw exception when updating accounting registry");
    }
}