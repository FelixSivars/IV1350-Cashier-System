package se.kth.iv1350.cashiersystem.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the cash register that stores payment.
 * Initial balance is set to 0.
 */
public class CashRegister {
    private float cashInRegister = 0f;
    private final List<RevenueObserver> revenueObserverList = new ArrayList<>();

    /**
     * Constructor for the <code>CashRegister</code> class.
     */
    public CashRegister() {
    }

    /**
     * Gets the current amount of cash stored in the register.
     *
     * @return The total amount of cash in the register.
     */
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
        updateAllObservers();
    }

    /**
     * Notifies all registered observers implementing the {@link RevenueObserver} interface
     * to update revenue by calling the updateRevenue method in the RevenueObserver.
     */
    public void updateAllObservers() {
        for (RevenueObserver observer : revenueObserverList) {
            observer.updateRevenue(cashInRegister);
        }
    }

    /**
     * Registers a {@link RevenueObserver} to be notified of revenue updates.
     *
     * @param observer The observer to be added to the notification list.
     */
    public void addRevenueObserver(RevenueObserver observer) {
        revenueObserverList.add(observer);
    }
}
