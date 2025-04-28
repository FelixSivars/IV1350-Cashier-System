package se.kth.iv1350.cashiersystem.dto;

import se.kth.iv1350.cashiersystem.model.Item;

import java.time.LocalDateTime;
import java.util.Collection;

// NOT IN USE
// Map<String, Integer> itemQuantityMap, String = ItemID & Integer = Quantity.
public record SaleDTO(LocalDateTime dateTime, float runningTotal, float vatTotal,
                      float amountPaid, float change, float discount, Collection<Item> itemsInCart) {
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public float getRunningTotal() {
        return runningTotal;
    }

    public float getVatTotal() {
        return vatTotal;
    }

    public float getAmountPaid() {
        return amountPaid;
    }

    public float getChange() {
        return change;
    }

    public float getDiscount() {
        return discount;
    }

    public Collection<Item> getItemsInCart() {
        return itemsInCart;
    }
}
