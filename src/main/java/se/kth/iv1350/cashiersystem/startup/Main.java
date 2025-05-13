package se.kth.iv1350.cashiersystem.startup;

import se.kth.iv1350.cashiersystem.controller.Controller;
import se.kth.iv1350.cashiersystem.controller.OperationFailureException;
import se.kth.iv1350.cashiersystem.integration.*;
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
    public static void main(String[] args) throws DatabaseFailureException, OperationFailureException {
        PrinterService printerService = PrinterService.getPrinterServiceInstance();
        RegistryCreator registryCreator = RegistryCreator.getRegistryCreatorInstance();
        Controller controller = new Controller(registryCreator, printerService);
        View view = new View(controller);

        //view.run();
        view.simulationRun();
    }
}