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

    /**
     * Gets the date and time when the sale has ended
     *
     * @return date and time at the end of the sale.
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Gets the current running total of the sale
     *
     * @return The total cost of all items in the sale
     */
    public float getRunningTotal() {
        return runningTotal;
    }

    /**
     * Gets the total VAT for the sale.
     *
     * @return The total VAT amount for the sale.
     */
    public float getVatTotal() {
        return vatTotal;
    }

    /**
     * Gets the amount paid by the customer
     *
     * @return The amount of money paid by the customer
     */
    public float getAmountPaid() {
        return amountPaid;
    }

    /**
     * Gets the amount of change that the customer is receiving
     *
     * @return The change returned to the customer after payment.
     */
    public float getChange() {
        return change;
    }

    /**
     * Gets the discount applied to the sale.
     *
     * @return The total discount amount applied to the sale.
     */
    public float getDiscount() {
        return discount;
    }

    /**
     * Gets the collection of ItemsInCartDTO included in the sale.
     *
     * @return A collection of <code>ItemsInCartDTO</code> objects in the cart.
     */
    public Collection<ItemInCartDTO> getItemsInCartDTO() {
        return itemsInCartDTO;
    }
}
