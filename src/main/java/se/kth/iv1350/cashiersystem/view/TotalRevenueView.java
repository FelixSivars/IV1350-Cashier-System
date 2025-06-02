package se.kth.iv1350.cashiersystem.view;
import se.kth.iv1350.cashiersystem.model.TemplateRevenueObserver;

import java.util.Locale;

/**
 * Displays the total revenue of all sales made since the program started in the console.
 * Extends {@link TemplateRevenueObserver} and defines console output behavior.
 */
public class TotalRevenueView extends TemplateRevenueObserver {

    /**
     * Displays the total revenue in the console.
     * 
     * @throws Exception If an error occurs during printing (rare for console).
     */
    @Override
    protected void doShowTotalIncome() {
        System.out.printf(Locale.US, "Balance in cash register: %.2f SEK%n", getTotalIncome());
    }


    /**
     * Handles any exceptions that occur while trying to display income to the console.
     *
     * @param e The exception that occurred.
     */
    @Override
    protected void handleErrors(Exception e) {
        System.out.println("Failed to display revenue: " + e.getMessage());
    }
}
