package se.kth.iv1350.cashiersystem.integration;

public class DatabaseFailureException extends Exception {
    /**
     * throw DatabaseFailureException when id = 'ghj789'
     */
    public DatabaseFailureException(String message) {
        super(message);
    }
}
