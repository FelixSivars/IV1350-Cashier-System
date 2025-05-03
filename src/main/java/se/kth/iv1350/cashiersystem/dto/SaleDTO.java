package se.kth.iv1350.cashiersystem.dto;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * DTO for sale details
 * This class is used to transfer item data between the integration layer and the model layer.
 * It includes details about a sale such as date and time of the sale, running total, vat total, amount paid, change given, discount applied and the items in the cart.
 */
public record SaleDTO(LocalDateTime dateTime, float runningTotal, float vatTotal,
                      float amountPaid, float change, float discount, Collection<ItemInCartDTO> itemsInCartDTO) {
}
