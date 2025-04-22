package se.kth.iv1350.cashiersystem.startup;

import se.kth.iv1350.cashiersystem.controller.Controller;
import se.kth.iv1350.cashiersystem.integration.DiscountCatalog;
import se.kth.iv1350.cashiersystem.integration.Printer;
import se.kth.iv1350.cashiersystem.integration.RegistryCreator;
import se.kth.iv1350.cashiersystem.view.View;

/**
 * Starts the entire application, contains the method used to start the application.
 */
public class Main {
    /**
     * The main method used to start the entire application.
     *
     * @param args, The application does not take any command line parameters.
     */
    public static void main(String[] args) {
        DiscountCatalog discountCatalog = new DiscountCatalog();
        Printer printer = new Printer();
        RegistryCreator registryCreator = new RegistryCreator();
        Controller controller = new Controller(registryCreator, printer);
        View view = new View(controller);

        view.simulationRun();
    }
}