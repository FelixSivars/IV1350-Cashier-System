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
     * @throws DatabaseFailureException when there is a connection issue to the database during start up.
     * @throws OperationFailureException when there is a general operation in the system that fails during runtime.
     * @param args, The application does not take any command line parameters.
     */
    public static void main(String[] args) throws DatabaseFailureException, OperationFailureException {
        PrinterService printerService = PrinterService.getInstance();
        RegistryCreator registryCreator = RegistryCreator.getInstance();
        Controller controller = new Controller(registryCreator, printerService);
        View view = new View(controller);

        view.run();
        //view.simulationRun();
    }
}