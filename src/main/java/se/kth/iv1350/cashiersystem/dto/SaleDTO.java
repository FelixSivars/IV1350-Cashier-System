package se.kth.iv1350.cashiersystem.dto;

import java.time.LocalDateTime;
import java.util.Map;

// Map<String, Integer> itemQuantityMap, String = ItemID & Integer = Quantity.
public record SaleDTO(LocalDateTime dateTime, float runningTotal, float vatTotal,
                      float amountPaid, float change, float discount, Map<String, Integer> itemQuantityMap) {

}
