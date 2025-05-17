package se.kth.iv1350.cashiersystem.integration;

/**
* An unchecked exception that is thrown when there is connection issues to the database.
*/
public class DatabaseFailureException extends RuntimeException {
    /**
     * throw DatabaseFailureException when id = 'ghj789' (hardcoded)
     */
    public DatabaseFailureException(String message) {
        super(message);
    }
}
