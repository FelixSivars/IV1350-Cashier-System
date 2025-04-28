package se.kth.iv1350.cashiersystem.integration;

import se.kth.iv1350.cashiersystem.dto.SaleDTO;
import se.kth.iv1350.cashiersystem.model.Item;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Locale;

/**
 * Represents the printer used for printing receipts.
 */
public class Printer {
    public void printReceipt(SaleDTO saleDTO) {
        System.out.println("----------------- Begin receipt -----------------");
        printTimeOfSale(saleDTO.getDateTime());
        System.out.println("");
        printItemsInCart(saleDTO.getItemsInCart());
        System.out.println("");
        printTotalPriceAndVat(saleDTO.getRunningTotal(), saleDTO.getVatTotal());
        System.out.println("");
        printAmountPaidAndChange(saleDTO.getAmountPaid(), saleDTO.getChange());
        System.out.println("------------------ End receipt ------------------");

    }

    private void printTimeOfSale(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String dateTime = localDateTime.format(formatter);
        System.out.println("Time of sale: " + dateTime);
    }

    private void printItemsInCart(Collection<Item> itemsInCart) {
        for (Item item : itemsInCart) {
            String itemName = item.getName();
            int itemQuantity = item.getQuantity();
            float itemPrice = item.getPrice();

            StringBuilder sb = new StringBuilder();
            sb.append(itemName);
            sb.append(" \t\t\t");
            sb.append(itemQuantity);
            sb.append(" x ");
            sb.append(itemPrice);
            sb.append(" \t");
            sb.append(itemQuantity * itemPrice);
            sb.append(" SEK");
            System.out.println(sb.toString());
        }
    }

    private void printTotalPriceAndVat(float runningTotal, float vat) {
        System.out.println("Total: \t\t\t\t\t\t\t\t\t" + runningTotal + " SEK");
        String s = String.format(Locale.US, "VAT: \t\t\t\t\t\t\t\t\t%.2f SEK", vat);
        System.out.println(s);
    }

    private void printAmountPaidAndChange(float amountPaid, float change) {
        System.out.println("Cash: \t\t\t\t\t\t\t\t\t" + amountPaid + " SEK");
        String s = String.format(Locale.US, "Change: \t\t\t\t\t\t\t\t%.2f SEK", change);
        System.out.println(s);

    }
}
