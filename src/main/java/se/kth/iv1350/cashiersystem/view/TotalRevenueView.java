package se.kth.iv1350.cashiersystem.view;

import se.kth.iv1350.cashiersystem.model.Observer;

public class TotalRevenueView implements Observer {

    @Override
    public void updateRevenue(float balance) {
        System.out.printf("Balance in cash register: %.2f SEK %n", balance);
    }
}
