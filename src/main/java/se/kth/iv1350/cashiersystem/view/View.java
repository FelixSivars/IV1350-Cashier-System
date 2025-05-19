package se.kth.iv1350.cashiersystem.view;

import se.kth.iv1350.cashiersystem.controller.Controller;
import se.kth.iv1350.cashiersystem.controller.OperationFailureException;
import se.kth.iv1350.cashiersystem.util.Logger;
import se.kth.iv1350.cashiersystem.util.TotalRevenueFileOutput;

import java.util.Locale;
import java.util.Scanner;

/**
 * This is a hardcoded simulation of the user interface.
 */
public class View {
    static final float AMOUNT_PAID = 100f;
    static final String UNEXPECTED_ERROR_MESSAGE = "An unexpected error occurred, please try again.";

    private final Controller controller;
    private final Logger consoleLogger = ConsoleLogger.getInstance();

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
     * A simulation of a sale transaction where you can enter values
     *
     */
    public void run() {
        String answer = "y";
        float payment = 0f;

        controller.startSale();
        System.out.println("A new sale has been started.");
        System.out.println(" ");
        System.out.println("Item Catalog:");
        System.out.println("abc123 - BigWheel Oatmeal");
        System.out.println("def456 - YouGoGo Blueberry");
        System.out.println("ghj789 - Gives database failure");
        System.out.println(" ");


        Scanner scanner = new Scanner(System.in);

        while (answer.equalsIgnoreCase("y")) {
            System.out.println("Enter item id: ");
            String itemId = scanner.next();
            System.out.println("Specify quantity: ");
            int quantity = scanner.nextInt();

            scanItem(itemId, quantity);

            System.out.println("Do you want to add more items? (y/n) ");
            answer = scanner.next();
        }


        System.out.println(" ");
        System.out.println("Sale End:");
        float totalCost = controller.endSale();
        System.out.printf(Locale.US, "Total cost (incl VAT): %.2f SEK%n", totalCost);
        System.out.println(" ");


        while (payment < totalCost) {
            System.out.println("Enter payment: ");
            payment = scanner.nextFloat();
            processPayment(payment);
            System.out.println(" ");
        }


        System.out.println("Do you want to start a new sale? (y/n)");
        if (scanner.next().equalsIgnoreCase("y"))
            run();
        else
            scanner.close();

    }

    /**
     * A simulation of a sale transaction
     *
     */
    public void simulationRun() {
        controller.startSale();
        System.out.println("A new sale has been started.");
        System.out.println(" ");

        scanItem("abc123", 1);
        scanItem("egerg", 1);
        scanItem("ghj789", 1);
        scanItem("def456", 2);


        System.out.println("Sale End:");
        float totalCost = controller.endSale();
        System.out.printf(Locale.US, "Total cost (incl VAT): %.2f SEK%n", totalCost);

        processPayment(AMOUNT_PAID);
    }

    private void processPayment(float amountPaid) {
        try {
            float change = controller.processPayment(amountPaid);
            System.out.printf(Locale.US, "Change to give to customer: %.2f SEK%n", change);
        } catch (OperationFailureException e) {
            switch (e.getCause().getClass().getSimpleName()) {
                case "InsufficientPaymentException" -> consoleLogger.log("Insufficient payment of " + amountPaid + " SEK for the minimum required amount of " + controller.getRunningTotal() + " SEK.");
                default -> consoleLogger.log(UNEXPECTED_ERROR_MESSAGE);
            }
        }
    }

    private void scanItem(String itemId, int quantity) {
        System.out.println("Scanned " + quantity + " item(s) with item id " + itemId);

        try {
            System.out.println(controller.scanItem(itemId, quantity));
            System.out.println(" ");

            System.out.printf(Locale.US, "Total cost (incl VAT): %.2f SEK%n", controller.getRunningTotal());
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
