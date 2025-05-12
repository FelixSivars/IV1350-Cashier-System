package se.kth.iv1350.cashiersystem.controller;

/**
* A checked exception that is thrown when a system operation fails,
* provides data for error handling in the overall system.
*/

public class OperationFailureException extends Exception {

    /**
     * Creates a new instance of OperationFailureException
     * with an error message and the cause of it being thrown.
     *
     * @param cause The exception that caused the system operation to fail.
     */
    public OperationFailureException(String message, Exception cause) {
        super(message, cause);
    }
}
