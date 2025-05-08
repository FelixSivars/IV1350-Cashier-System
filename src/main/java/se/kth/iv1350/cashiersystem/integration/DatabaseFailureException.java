package se.kth.iv1350.cashiersystem.integration;

public class DatabaseFailureException extends RuntimeException {
    /**
     * throw DatabaseFailureException when id = 'ghj789'
     */
    public DatabaseFailureException() {
        super("The database could not be reached.");
    }
}
