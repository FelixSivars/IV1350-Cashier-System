package se.kth.iv1350.cashiersystem.view;

import se.kth.iv1350.cashiersystem.model.RevenueObserver;

import java.util.Locale;

public class TotalRevenueView implements RevenueObserver {

    @Override
    public void updateRevenue(float balance) {
        System.out.printf(Locale.US,"Balance in cash register: %.2f SEK %n", balance);
    }
}
