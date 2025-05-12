package se.kth.iv1350.cashiersystem.view;

import se.kth.iv1350.cashiersystem.util.Logger;

public class ConsoleLogger implements Logger {
    @Override
    public void log(Exception e) {
        String message = "Error: " + e.getMessage();

        System.out.println(message);
    }
}
