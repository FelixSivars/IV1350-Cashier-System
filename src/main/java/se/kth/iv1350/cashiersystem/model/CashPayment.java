package se.kth.iv1350.cashiersystem.model;


/**
* Represents a payment made by cash by the customer.
*/
public class CashPayment {
    private float amountPaid;
    private float totalPrice;
    private float change;

 /**
     * Constructor for creating a <code>CashPayment</code> object.
     * Initializes the <code>amountPaid</code> and <code>totalPrice</code>.
     * Calls <code>calculateChange</code> method to calculate the change.
     *
     * @param amountPaid The amount paid by the customer.
     * @param totalPrice The total price of the sale.
     */
    public CashPayment(float amountPaid, float totalPrice) {
        this.amountPaid = amountPaid;
        this.totalPrice = totalPrice;

        calculateChange();
    }

/**
* Calculates the amount of change that the customer is receiving
*/
    public void calculateChange() {
        change = amountPaid - totalPrice;
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
    
/**
* Gets the total price of the sale.
*
* @return The total price of the sale.
*/
    public float getTotalPrice() {
        return totalPrice;
    }
}
