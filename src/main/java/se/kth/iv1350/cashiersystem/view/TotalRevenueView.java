package se.kth.iv1350.cashiersystem.view;

import se.kth.iv1350.cashiersystem.model.RevenueObserver;

import java.util.Locale;

/**
 * A {@link RevenueObserver} implementation that displays the total revenue
 * in the console whenever an update is received.
 */
public class TotalRevenueView implements RevenueObserver {

    /**
     * Displays the updated total revenue in the console.
     *
     * @param balance The updated revenue value to display.
     */
    @Override
    public void updateRevenue(float balance) {
        System.out.printf(Locale.US,"Balance in cash register: %.2f SEK %n", balance);
    }
}
