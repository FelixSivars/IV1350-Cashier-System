package se.kth.iv1350.cashiersystem.controller;

import se.kth.iv1350.cashiersystem.dto.ItemDTO;
import se.kth.iv1350.cashiersystem.dto.SaleDTO;
import se.kth.iv1350.cashiersystem.integration.*;
import se.kth.iv1350.cashiersystem.model.*;
import se.kth.iv1350.cashiersystem.util.FileLogger;

/**
 * This is the application's only controller, it handles system operations and coordinates interactions between the
 * View-, Model- and Integration layers.
 */
public class Controller {
    private final PrinterService printerService;
    private InventoryRegistryHandler inventoryRegistryHandler;
    private final AccountingRegistryHandler accountingRegistryHandler;
    private final CashRegister cashRegister = new CashRegister();
    private final FileLogger fileLogger = FileLogger.getInstance();
    private Sale sale;

    /**
     * Creates a new instance of <code>Controller</code>.
     *
     * @param registryCreator The creator of external registries.
     * @param printerService         The {@link PrinterService} that prints the receipt.
     */
    public Controller(RegistryCreator registryCreator, PrinterService printerService) {
        this.printerService = printerService;
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
     * @throws OperationFailureException if the inventory catalog call failed
     * or if the item ID is invalid.
     */
    public ItemDTO scanItem(String itemId, int quantity) throws OperationFailureException {
        try {
            ItemDTO itemDTO = getItemDTOFromId(itemId);

            if (sale.isItemInSale(itemId)) {
                sale.increaseQuantity(itemId, quantity);
            } else {
                Item item = itemDTO.toItem();
                sale.addItem(item, quantity);
            }
            return itemDTO;
        } catch (InvalidItemIdException e) {
            throw new OperationFailureException(e);
        } catch (DatabaseFailureException e) {
            OperationFailureException operationFailureException = new OperationFailureException(e);
            fileLogger.exceptionLog(operationFailureException);
            throw  operationFailureException;
        }
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
     * {@link PrinterService} receives {@link SaleDTO} and prints it out,
     * updates external/internal systems with the data from {@link SaleDTO} and <code>totalPrice</code>.
     *
     * @param amountPaid The amount of money paid by the customer.
     * @return The amount of change to give back to the customer.
     * @throws OperationFailureException if the paid amount is insufficient to the total price.
     */
    public float processPayment(float amountPaid) throws OperationFailureException {
        try {
            float totalPrice = sale.getRunningTotal();

            CashPayment cashPayment = new CashPayment(amountPaid, totalPrice);
            sale.setCashPayment(cashPayment);

            SaleDTO saleDTO = sale.toDTO();
            printerService.printReceipt(saleDTO);
            updateSystems(saleDTO, totalPrice);
            return cashPayment.getChange();

        } catch (InsufficientPaymentException e) {
            throw new OperationFailureException(e);
        }
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
    public ItemDTO getItemDTOFromId(String itemId) throws InvalidItemIdException, DatabaseFailureException {
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

    public void addRevenueObserver(Observer observer) {
        cashRegister.addObserver(observer);
    }
}

