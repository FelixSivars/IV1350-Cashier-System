package se.kth.iv1350.cashiersystem.model;

/**
 * A base class for revenue observers that uses the Template Method pattern.
 * It calculates the total income and provides a framework for displaying it,
 * while allowing subclasses to define their own way of presenting the result and handling errors.
 */
public abstract class TemplateRevenueObserver implements RevenueObserver {
    private float totalIncome;

    /**
     * Called when a new sale is made. Calculates the new total income
     * and displays it using the subclass-defined methods.
     *
     * @param priceOfSale The price of the most recent sale.
     */
    @Override
    public void updateRevenue(float priceOfSale) {
        calculateTotalIncome(priceOfSale);
        showTotalIncome();
    }

    /**
     * Adds the given sale price to the running total income.
     *
     * @param amount The amount paid in the recent sale.
     */
    private void calculateTotalIncome(float amount) {
        totalIncome += amount;
    }

    /**
     * Tries to show the total income and delegates error handling to subclass if any exceptions occur.
     */
    private void showTotalIncome() {
        try {
            doShowTotalIncome();
        } catch (Exception e) {
            handleErrors(e);
        }
    }

    /**
     * Returns the current accumulated total income since the program started.
     *
     * @return The total income.
     */
    protected float getTotalIncome() {
        return totalIncome;
    }

    /**
     * Displays the total income.
     * This method must be implemented by subclasses to define how income is shown.
     *
     * @throws Exception If an error occurs while displaying the income.
     */
    protected abstract void doShowTotalIncome() throws Exception;

    /**
     * Handles any exceptions thrown while displaying the total income.
     * Subclasses define how to log or report the error.
     *
     * @param e The exception that was thrown.
     */
    protected abstract void handleErrors(Exception e);
}

