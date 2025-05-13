package se.kth.iv1350.cashiersystem.view;

import se.kth.iv1350.cashiersystem.controller.Controller;
import se.kth.iv1350.cashiersystem.controller.OperationFailureException;
import se.kth.iv1350.cashiersystem.util.Logger;
import se.kth.iv1350.cashiersystem.util.TotalRevenueFileOutput;

import java.util.Locale;

/**
 * This is a hardcoded simulation of the user interface.
 */
public class View {
    static final float AMOUNT_PAID = 100f;
    static final String UNEXPECTED_ERROR_MESSAGE = "An unexpected error occurred, please try again.";

    private final Controller controller;
    private final Logger consoleLogger = ConsoleLogger.getConsoleLoggerInstance();

    /**
     * Constructs a new <code>View</code> object.
     *
     * @param controller The {@link Controller} that manages the system operations.
     */
    public View(Controller controller) {
        this.controller = controller;
        controller.addRevenueObserver(new TotalRevenueView());
        controller.addRevenueObserver(new TotalRevenueFileOutput());
    }

    /**
     * A simulation of a sale transaction
     *
     */
    public void simulationRun() throws OperationFailureException {
        controller.startSale();
        System.out.println("A new sale has been started.");
        System.out.println(" ");

        scanItem("abc123", 1);
        scanItem("egerg", 1);
        scanItem("ghj789", 1);

        System.out.println("Sale End:");
        System.out.println("Total cost (incl VAT): " + controller.endSale() + " SEK");

        try {
            float change = controller.processPayment(AMOUNT_PAID);
            System.out.printf(Locale.US, "Change to give to customer: %.2f SEK%n", change);
        } catch (OperationFailureException e) {
            switch (e.getCause().getClass().getSimpleName()) {
                case "InsufficientPaymentException" -> consoleLogger.log("Insufficient payment of " + AMOUNT_PAID + " SEK for the minimum required amount of " + controller.getRunningTotal() + " SEK.");
                default -> consoleLogger.log(UNEXPECTED_ERROR_MESSAGE);
            }
        }
    }

    private void scanItem(String itemId, int quantity) {
        System.out.println("Scanned " + quantity + " item(s) with item id " + itemId);

        try {
            System.out.println(controller.scanItem(itemId, quantity));
            System.out.println(" ");

            System.out.println("Total cost (incl VAT): " + controller.getRunningTotal() + " SEK");
            System.out.printf(Locale.US, "Total VAT: %.2f SEK%n", controller.getVatTotal());
        } catch (OperationFailureException e) {
            switch (e.getCause().getClass().getSimpleName()) {
                case "InvalidItemIdException" -> consoleLogger.log("Could not add the item to the sale, invalid item identifier.");
                case "DatabaseFailureException" -> consoleLogger.log("Connection failed, please try again later.");
                default -> consoleLogger.log(UNEXPECTED_ERROR_MESSAGE);
            }
        }
        System.out.println(" ");
    }
}
