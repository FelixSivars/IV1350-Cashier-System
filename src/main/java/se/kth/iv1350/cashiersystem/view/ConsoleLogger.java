package se.kth.iv1350.cashiersystem.view;

import se.kth.iv1350.cashiersystem.util.Logger;

public class ConsoleLogger implements Logger {
    private static final ConsoleLogger CONSOLE_LOGGER = new ConsoleLogger();

    public static ConsoleLogger getInstance() {
        return CONSOLE_LOGGER;
    }

    @Override
    public void exceptionLog(Exception e) {
        String message = "Error: " + e.getMessage();
        System.out.println(message);
    }

    @Override
    public void log(String message) {
        System.out.println("Error: " + message);
    }
}
