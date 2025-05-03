package se.kth.iv1350.cashiersystem.view;

import se.kth.iv1350.cashiersystem.controller.Controller;

import java.util.Locale;

/**
 * This is a hardcoded simulation of the user interface.
 */
public class View {
    static final float AMOUNT_PAID = 100f;

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
     */
    public void simulationRun() {
        controller.startSale();
        System.out.println("A new sale has been started.");
        System.out.println(" ");

        addItem("abc123", 1);
        addItem("abc123", 1);
        addItem("def456", 1);


        System.out.println("Sale End:");
        System.out.println("Total cost (incl VAT): " + controller.endSale() + " SEK");

        float change = controller.processPayment(AMOUNT_PAID);
        System.out.printf(Locale.US, "Change to give to customer: %.2f SEK%n", change);
    }

    private void addItem(String itemId, int quantity) {
        System.out.println("Add " + quantity + " item(s) with item id " + itemId);
        System.out.println(controller.scanItem(itemId, quantity));
        System.out.println(" ");

        System.out.println("Total cost (incl VAT): " + controller.getRunningTotal() + " SEK");
        System.out.printf(Locale.US, "Total VAT: %.2f SEK%n", controller.getVatTotal());
        System.out.println(" ");
    }
}
