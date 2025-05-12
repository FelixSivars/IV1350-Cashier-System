package se.kth.iv1350.cashiersystem.integration;


/**
* A checked exception that is thrown when an invalid item ID is encountered during a sale.
*/
public class InvalidItemIdException extends Exception {

    /**
     * Creates a new instance of <code>InvalidItemIdException</code> with an error message
     * indicating that the scanned item ID is invalid
     *
     * @param itemId The item ID that was not found in the inventory catalog.
     */
    public InvalidItemIdException(String message) {
        super(message);
    }
}
