package se.kth.iv1350.cashiersystem.view;

import se.kth.iv1350.cashiersystem.util.Logger;

/**
 * A singleton implementation of the <code>Logger</code> interface that logs messages and exceptions to the console.
 */ 
public class ConsoleLogger implements Logger {
    private static final ConsoleLogger CONSOLE_LOGGER = new ConsoleLogger();

    /**
     * Returns the single instance of <code>ConsoleLogger</code>.
     *
     * @return The singleton instance of <code>ConsoleLogger</code>.
     */
    public static ConsoleLogger getInstance() {
        return CONSOLE_LOGGER;
    }

    /**
     * Logs an exception to the console with its message.
     *
     * @param e The exception to log.
     */
    @Override
    public void exceptionLog(Exception e) {
        String message = "Error: " + e.getMessage();
        System.out.println(message);
    }

    /**
     * Logs a general message to the console.
     *
     * @param message The message to be logged.
     */
    @Override
    public void log(String message) {
        System.out.println("Error: " + message);
    }
}
