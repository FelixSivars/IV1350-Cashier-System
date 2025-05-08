package se.kth.iv1350.cashiersystem.integration;

import se.kth.iv1350.cashiersystem.dto.ItemInCartDTO;
import se.kth.iv1350.cashiersystem.dto.SaleDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Locale;

/**
 * Handles the printer used for printing receipts.
 */
public class PrinterService {

    /**
     * Prints the receipt for the completed sale.
     * The receipt includes the date and time of the sale, item details, total price, total VAT, amount paid by the customer and the change returned.
     *
     * @param saleDTO The {@link SaleDTO} containing all relevant sale information to be printed.
     */
    public void printReceipt(SaleDTO saleDTO) {
        System.out.println("----------------- Begin receipt -----------------");
        printTimeOfSale(saleDTO.dateTime());
        System.out.println(" ");
        printItemsInCart(saleDTO.itemsInCartDTO());
        System.out.println(" ");
        printTotalPriceAndVat(saleDTO.runningTotal(), saleDTO.vatTotal());
        System.out.println(" ");
        printAmountPaidAndChange(saleDTO.amountPaid(), saleDTO.change());
        System.out.println("------------------ End receipt ------------------");

    }

    private void printTimeOfSale(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String dateTime = localDateTime.format(formatter);
        System.out.println("Time of sale: " + dateTime);
    }

    private void printItemsInCart(Collection<ItemInCartDTO> itemsInCartDTO) {
        for (ItemInCartDTO item : itemsInCartDTO) {
            String itemName = item.itemDTO().name();
            int itemQuantity = item.quantity();
            float itemPrice = item.itemDTO().price();

            String itemInCartLine = itemName + " \t\t\t" + itemQuantity + " x " +
                    itemPrice + " \t" + itemQuantity * itemPrice + " SEK";
            System.out.println(itemInCartLine);
        }
    }

    private void printTotalPriceAndVat(float runningTotal, float vat) {
        System.out.println("Total: \t\t\t\t\t\t\t\t\t" + runningTotal + " SEK");
        System.out.printf(Locale.US, "VAT: \t\t\t\t\t\t\t\t\t%.2f SEK\n", vat);
    }

    private void printAmountPaidAndChange(float amountPaid, float change) {
        System.out.println("Cash: \t\t\t\t\t\t\t\t\t" + amountPaid + " SEK");
        System.out.printf(Locale.US, "Change: \t\t\t\t\t\t\t\t%.2f SEK \n", change);
    }
}
