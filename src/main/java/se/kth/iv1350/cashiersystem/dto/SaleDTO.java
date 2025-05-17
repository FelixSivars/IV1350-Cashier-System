package se.kth.iv1350.cashiersystem.dto;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * DTO for sale details
 * This class is used to transfer item data between the integration layer and the model layer.
 * It includes details about a sale such as date and time of the sale, running total, vat total, amount paid, change given and the items in the cart.
 */
public record SaleDTO(LocalDateTime dateTime, float runningTotal, float vatTotal,
                      float amountPaid, float change, Collection<ItemInCartDTO> itemsInCartDTO) {

    /**
     * A builder class for creating a {@link saleDTO} instance using the Builder design pattern.
     */
    public static final class Builder {
        private LocalDateTime dateTime;
        private float runningTotal;
        private float vatTotal;
        private float amountPaid;
        private float change;
        private Collection<ItemInCartDTO> itemsInCartDTO;

        /**
         * Sets the date and time of the sale.
         *
         * @param dateTime The date and time of the sale.
         * @return This builder instance for method chaining.
         */
        public SaleDTO.Builder dateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        /**
         * Sets the running total of the sale.
         *
         * @param runningTotal The running total of the sale.
         * @return This builder instance for method chaining.
         */
        public SaleDTO.Builder runningTotal(float runningTotal) {
            this.runningTotal = runningTotal;
            return this;
        }

        /**
         * Sets the VAT total of the sale.
         *
         * @param runningTotal The VAT total of the sale.
         * @return This builder instance for method chaining.
         */
        public SaleDTO.Builder vatTotal(float vatTotal) {
            this.vatTotal = vatTotal;
            return this;
        }

         /**
         * Sets the amount paid for the sale by customer.
         *
         * @param amountPaid The amount paid by the customer.
         * @return This builder instance for method chaining.
         */
        public SaleDTO.Builder amountPaid(float amountPaid) {
            this.amountPaid = amountPaid;
            return this;
        }

        /**
         * Sets the amount of change that should be given to the customer.
         *
         * @param change The amount of change.
         * @return This builder instance for method chaining.
         */
        public SaleDTO.Builder change(float change) {
            this.change = change;
            return this;
        }

        /**
         * Sets the collection of items included in the sale.
         *
         * @param itemsInCartDTO A collection of {@link ItemInCartDTO} representing the items in the cart.
         * @return This builder instance for method chaining.
         */
        public SaleDTO.Builder itemsInCartDTO(Collection<ItemInCartDTO> itemsInCartDTO) {
            this.itemsInCartDTO = itemsInCartDTO;
            return this;
        }

        /**
         * Builds a new {@link SaleDTO} instance using the current builder values.
         *
         * @return A {@code SaleDTO} object.
         */
        public SaleDTO build() {
            return new SaleDTO(dateTime, runningTotal, vatTotal, amountPaid, change, itemsInCartDTO);
        }
    }
}
