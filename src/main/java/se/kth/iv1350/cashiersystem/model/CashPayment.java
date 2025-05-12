package se.kth.iv1350.cashiersystem.model;


/**
 * Represents a payment made by cash by the customer.
 */
public class CashPayment {
    private final float amountPaid;
    private float change;

    /**
     * Constructor for creating a <code>CashPayment</code> object.
     * Initializes the <code>amountPaid</code> and <code>totalPrice</code>.
     * Calls <code>calculateChange</code> method to calculate the change.
     *
     * @param amountPaid The amount paid by the customer.
     * @param totalPrice The total price of the sale.
     */
    public CashPayment(float amountPaid, float totalPrice) throws InsufficientPaymentException {
        this.amountPaid = amountPaid;

        calculateChange(totalPrice);
    }

    /**
    * @throws InsufficientPaymentException if the change between the amount paid and the
    * total price is less than 0.
    */
    private void calculateChange(float totalPrice) throws InsufficientPaymentException {
        change = amountPaid - totalPrice;

        if (change < 0)
            throw new InsufficientPaymentException("Insufficient payment of " + amountPaid + " SEK for the minimum required amount of " + totalPrice + " SEK.");
    }

    /**
     * Gets the change of the sale.
     *
     * @return The change of the sale.
     */
    public float getChange() {
        return change;
    }

    /**
     * Gets the amount paid by the customer.
     *
     * @return The amount paid by the customer.
     */
    public float getAmountPaid() {
        return amountPaid;
    }
}
