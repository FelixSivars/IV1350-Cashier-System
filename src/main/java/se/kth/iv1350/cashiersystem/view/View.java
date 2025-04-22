package se.kth.iv1350.cashiersystem.view;

import se.kth.iv1350.cashiersystem.controller.Controller;

/**
 * This is a hardcoded simulation of the user interface.
 */
public class View {
    private Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void simulationRun() {
        controller.startSale();
        System.out.println("A new sale has been started.");

        controller.scanItem("abc123", 2);
        System.out.println(controller.getRunningTotal());
        System.out.printf("%.2f%n", controller.getVatTotal());

        controller.scanItem("def456", 1);

        System.out.println(controller.getRunningTotal());
        System.out.printf("%.2f%n", controller.getVatTotal());

        controller.endSale();
        System.out.println("Sale End.");

        controller.makePayment(100f);
    }
}
