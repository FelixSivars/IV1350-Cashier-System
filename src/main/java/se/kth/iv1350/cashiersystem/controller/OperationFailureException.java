package se.kth.iv1350.cashiersystem.controller;

public class OperationFailureException extends Exception {
    public OperationFailureException(Exception cause) {
        super("Failed to perform a system operation.", cause);
    }
}
