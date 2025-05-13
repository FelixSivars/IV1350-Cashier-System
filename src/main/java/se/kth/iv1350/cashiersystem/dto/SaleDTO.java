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


    public static final class Builder {
        private LocalDateTime dateTime;
        private float runningTotal;
        private float vatTotal;
        private float amountPaid;
        private float change;
        private Collection<ItemInCartDTO> itemsInCartDTO;

        public SaleDTO.Builder dateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public SaleDTO.Builder runningTotal(float runningTotal) {
            this.runningTotal = runningTotal;
            return this;
        }

        public SaleDTO.Builder vatTotal(float vatTotal) {
            this.vatTotal = vatTotal;
            return this;
        }

        public SaleDTO.Builder amountPaid(float amountPaid) {
            this.amountPaid = amountPaid;
            return this;
        }

        public SaleDTO.Builder change(float change) {
            this.change = change;
            return this;
        }

        public SaleDTO.Builder itemsInCartDTO(Collection<ItemInCartDTO> itemsInCartDTO) {
            this.itemsInCartDTO = itemsInCartDTO;
            return this;
        }

        public SaleDTO build() {
            return new SaleDTO(dateTime, runningTotal, vatTotal, amountPaid, change, itemsInCartDTO);
        }
    }
}
