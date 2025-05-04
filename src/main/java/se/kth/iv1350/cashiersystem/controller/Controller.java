package se.kth.iv1350.cashiersystem.controller;

import se.kth.iv1350.cashiersystem.dto.ItemDTO;
import se.kth.iv1350.cashiersystem.dto.SaleDTO;
import se.kth.iv1350.cashiersystem.integration.AccountingRegistryHandler;
import se.kth.iv1350.cashiersystem.integration.InventoryRegistryHandler;
import se.kth.iv1350.cashiersystem.integration.Printer;
import se.kth.iv1350.cashiersystem.integration.RegistryCreator;
import se.kth.iv1350.cashiersystem.model.CashPayment;
import se.kth.iv1350.cashiersystem.model.CashRegister;
import se.kth.iv1350.cashiersystem.model.Item;
import se.kth.iv1350.cashiersystem.model.Sale;

/**
 * This is the application's only controller, it handles system operations and coordinates interactions between the
 * View-, Model- and Integration layers.
 */
public class Controller {
    private final Printer printer;
    private InventoryRegistryHandler inventoryRegistryHandler;
    private final AccountingRegistryHandler accountingRegistryHandler;
    private final CashRegister cashRegister = new CashRegister();
    private Sale sale;

    /**
     * Creates a new instance of <code>Controller</code>.
     *
     * @param registryCreator The creator of external registries.
     * @param printer         The {@link Printer} that prints the receipt.
     */
    public Controller(RegistryCreator registryCreator, Printer printer) {
        this.printer = printer;
        this.inventoryRegistryHandler = registryCreator.getInventoryRegistryHandler();
        this.accountingRegistryHandler = registryCreator.getAccountingRegistryHandler();
    }

    /**
     * Sets the {@link InventoryRegistryHandler} for the controller.
     * Used for test cases.
     *
     * @param inventoryRegistryHandler The {@link InventoryRegistryHandler} object containing an item catalog.
     */
    public void setInventoryRegistryHandler(InventoryRegistryHandler inventoryRegistryHandler) {
        this.inventoryRegistryHandler = inventoryRegistryHandler;
    }

    /**
     * Method to start a new sale.
     */
    public void startSale() {
        this.sale = new Sale();
    }

    /**
     * Retrieves the current running total of the sale.
     *
     * @return The running total of the sale.
     */
    public float getRunningTotal() {
        return sale.getRunningTotal();
    }

    /**
     * Retrieves the current running total VAT of the sale.
     *
     * @return The running total VAT of the sale.
     */
    public float getVatTotal() {
        return sale.getVatTotal();
    }

    /**
     * Processes the scanning of an item by its identifier and the quantity specified.
     * If the item is already in the sale, its quantity is updated.
     * Otherwise, the item is fetched from the inventory and added to the sale.
     *
     * @param itemId   The identifier of the item being scanned.
     * @param quantity The number of units of the item being scanned.
     *
     * @return The item details of the item getting scanned
     */
    public ItemDTO scanItem(String itemId, int quantity) {
        if (!inventoryRegistryHandler.isValidItemId(itemId)) {
            return null;
        }
        ItemDTO itemDTO = getItemDTOFromId(itemId);

        if (sale.isItemInSale(itemId)) {
            sale.increaseQuantity(itemId, quantity);
        } else {
            Item item = itemDTO.toItem();
            sale.addItem(item, quantity);
        }
        return itemDTO;
    }

    /**
     * Ends the current sale and returns the total amount including VAT of the sale.
     *
     * @return The total price of the sale including VAT.
     */
    public float endSale() {
        return sale.endSale();
    }

    /**
     * Processes the payment made by the customer.
     * Creates a new instance of {@link CashPayment} and sets the amount to the amount paid by the customer,
     * creates a {@link SaleDTO} from {@link Sale},
     * {@link Printer} receives {@link SaleDTO} and prints it out,
     * updates external/internal systems with the data from {@link SaleDTO} and <code>totalPrice</code>.
     *
     * @param amountPaid The amount of money paid by the customer.
     * @return The amount of change to give back to the customer.
     */
    public float processPayment(float amountPaid) {
        float totalPrice = sale.getRunningTotal();

        // Validate payment amount is sufficient
        if (amountPaid < totalPrice) {
            System.out.println("Error: Insufficient payment. Amount paid: " + amountPaid + 
                            ", Required: " + totalPrice);
            return -1; // Using a negative value to indicate error
        }

        CashPayment cashPayment = new CashPayment(amountPaid, totalPrice);
        sale.setCashPayment(cashPayment);

        SaleDTO saleDTO = sale.toDTO();
        printer.printReceipt(saleDTO);
        updateSystems(saleDTO, totalPrice);
        return cashPayment.getChange();
    }

    private void updateSystems(SaleDTO saleDTO, float totalPrice) {
        cashRegister.addCashInRegister(totalPrice);
        accountingRegistryHandler.updateAccountingRegistry(saleDTO);
        inventoryRegistryHandler.updateInventoryRegistry(saleDTO);
    }

    /**
     * Fetches item details from inventory registry.
     *
     * @param itemId The item identification.
     * @return Item details.
     */
    public ItemDTO getItemDTOFromId(String itemId) {
        return inventoryRegistryHandler.fetchItemById(itemId);
    }

    /**
     * Gets the current sale details as a DTO.
     * This method is primarily intended for testing purposes.
     *
     * @return The current sale as a {@link SaleDTO} object.
     */
    public SaleDTO getSaleDTO() {
        return sale.toDTO();
    }
}

