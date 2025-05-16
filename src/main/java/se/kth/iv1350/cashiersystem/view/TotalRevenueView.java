package se.kth.iv1350.cashiersystem.view;

import se.kth.iv1350.cashiersystem.model.Observer;

import java.util.Locale;

public class TotalRevenueView implements Observer {

    @Override
    public void updateRevenue(float balance) {
        System.out.printf(Locale.US,"Balance in cash register: %.2f SEK %n", balance);
    }
}
