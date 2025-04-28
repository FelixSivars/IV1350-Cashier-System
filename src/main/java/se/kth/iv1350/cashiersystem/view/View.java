package se.kth.iv1350.cashiersystem.view;

import se.kth.iv1350.cashiersystem.controller.Controller;
import se.kth.iv1350.cashiersystem.dto.ItemDTO;

import java.util.Locale;

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
        System.out.println("");

        addItem("abc123", 1);
        addItem("abc123", 1);

        addItem("def456", 1);


        controller.endSale();
        System.out.println("Sale End.");

        controller.makePayment(100f);
    }

    private void addItem(String itemId, int quantity) {
        controller.scanItem(itemId, quantity);
        ItemDTO itemDTO = controller.getItemDTOfromId(itemId);

        System.out.println("Add 1 item with item id " + itemId);
        System.out.println("Item ID: " + itemId);
        System.out.println("Item name: " + itemDTO.name());
        System.out.println("Item cost: " + itemDTO.price());
        System.out.println("VAT: " + itemDTO.vatPercentage() + " %");
        System.out.println("Item description: " + itemDTO.description());

        System.out.println(" ");

        System.out.println("Total cost (incl VAT): " + controller.getRunningTotal());

        String s = String.format(Locale.US, "Total VAT: %.2f%n", controller.getVatTotal());
        System.out.println(s);

    }
}
