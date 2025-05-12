package se.kth.iv1350.cashiersystem.model;

/**
 * A checked exception that is thrown when the amount paid by the customer is less than the total price of the sale.
 */
public class InsufficientPaymentException extends Exception {

     /**
     * Creates an instance of {@code InsufficientPaymentException} with a message that communicates the amount paid and 
     * the total price required.
     *
     * @param amountPaid The amount paid by the customer.
     * @param totalPrice The total price required for the purchase.
     */
    public InsufficientPaymentException(String message) {
        super(message);
    }
}
