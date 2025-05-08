package se.kth.iv1350.cashiersystem.model;

public class InsufficientPaymentException extends Exception {
    public InsufficientPaymentException(float amountPaid, float totalPrice) {
        super("Insufficient payment of " + amountPaid + " SEK for the minimum required amount of " + totalPrice + " SEK.");
    }
}
