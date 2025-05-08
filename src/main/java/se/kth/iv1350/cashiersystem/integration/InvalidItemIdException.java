package se.kth.iv1350.cashiersystem.integration;

public class InvalidItemIdException extends Exception {
    public InvalidItemIdException(String itemId) {
        super("The item with the id '" + itemId + "' is invalid.");
    }
}
