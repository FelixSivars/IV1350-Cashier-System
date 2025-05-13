package se.kth.iv1350.cashiersystem.view;

import se.kth.iv1350.cashiersystem.model.Observer;

public class TotalRevenueView implements Observer {

    @Override
    public void updateRevenue(float balance) {
        System.out.println("Balance in cash register: " + balance + " SEK");
    }
}
