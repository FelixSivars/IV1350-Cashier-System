package se.kth.iv1350.cashiersystem.view;

import se.kth.iv1350.cashiersystem.controller.Controller;
import se.kth.iv1350.cashiersystem.controller.OperationFailureException;
import se.kth.iv1350.cashiersystem.integration.DatabaseFailureException;
import se.kth.iv1350.cashiersystem.integration.InvalidItemIdException;

import java.util.Locale;

/**
 * This is a hardcoded simulation of the user interface.
 */
public class View {
    static final float AMOUNT_PAID = 10f;

    private Controller controller;

    /**
     * Constructs a new <code>View</code> object.
     *
     * @param controller The {@link Controller} that manages the system operations.
     */
    public View(Controller controller) {
        this.controller = controller;
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
        scanItem("def456", 1);



        System.out.println("Sale End:");
        System.out.println("Total cost (incl VAT): " + controller.endSale() + " SEK");

        float change = controller.processPayment(AMOUNT_PAID);
        System.out.printf(Locale.US, "Change to give to customer: %.2f SEK%n", change);
    }

    private void scanItem(String itemId, int quantity) {
        System.out.println("Scanned " + quantity + " item(s) with item id " + itemId);

        try {
            System.out.println(controller.scanItem(itemId, quantity));
            System.out.println(" ");

            System.out.println("Total cost (incl VAT): " + controller.getRunningTotal() + " SEK");
            System.out.printf(Locale.US, "Total VAT: %.2f SEK%n", controller.getVatTotal());
        } catch (OperationFailureException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(" ");
    }
}
