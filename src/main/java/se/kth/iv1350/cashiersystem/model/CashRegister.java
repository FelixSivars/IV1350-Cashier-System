package se.kth.iv1350.cashiersystem.model;

/**
 * Represents the cash register that stores payment
 */
public class CashRegister {
    private float cashInRegister;

    /**
     * Constructor for the <code>CashRegister</code> class.
     */
    public CashRegister() {
    }
    
    /**
     * Updates the balance in the cash register after a completed sale.
     * Adds the total price of the sale to the current cash balance.
     *
     * @param totalPrice The total price of the completed sale.
     */
    public void updateCashInRegister(float totalPrice) {
        cashInRegister += totalPrice;
    }
}
