package se.kth.iv1350.cashiersystem.model;

/**
 * Represents the cash register that stores payment.
 * Initial balance is set to 0.
 */
public class CashRegister {
    private float cashInRegister = 0f;

    /**
     * Constructor for the <code>CashRegister</code> class.
     */
    public CashRegister() {
    }

    public float getCashInRegister() {
        return cashInRegister;
    }

    /**
     * Updates the balance in the cash register after a completed sale.
     * Adds the total price of the sale to the current cash balance.
     *
     * @param totalPrice The total price of the completed sale.
     */
    public void addCashInRegister(float totalPrice) {
        cashInRegister += totalPrice;
    }
}
