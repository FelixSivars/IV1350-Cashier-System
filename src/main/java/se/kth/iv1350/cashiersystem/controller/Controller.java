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
 * This is the applications only controller, it handles system operations and coordinates interactions between the
 * View-, Model- and Integration layers.
 */
public class Controller {
    private final Printer printer;
    private final InventoryRegistryHandler inventoryRegistryHandler;
    private final AccountingRegistryHandler accountingRegistryHandler;
    private final CashRegister cashRegister = new CashRegister();
    private Sale sale;

     /**
     * Creates a new instance of Controller.
     *
     * @param registryCreator The creator of external registries
     * @param printer         The printer that prints the receipt
     */
    public Controller(RegistryCreator registryCreator, Printer printer) {
        this.printer = printer;
        this.inventoryRegistryHandler = registryCreator.getInventoryRegistryHandler();
        this.accountingRegistryHandler = registryCreator.getAccountingRegistryHandler();
    }

     /**
     * Method to start a new sale
     */
    public void startSale() {
        this.sale = new Sale();
    }

     /**
     * Retrieves the current running total of the sale
     *
     * @return The running total of the sale.
     */
    public float getRunningTotal() {
        return sale.getRunningTotal();
    }

    /**
     * Retrieves the current running total VAT of the sale
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
     */
    public void scanItem(String itemId, int quantity) {
        if (inventoryRegistryHandler.isValidItemId(itemId)) {
            if (sale.isItemInSale(itemId)) {
                sale.updateQuantity(itemId, quantity);
            } else {
                ItemDTO itemDTO = getItemDTOfromId(itemId);
                Item item = itemDTO.toItem();
                sale.addItem(item, quantity);
            }
        }
    }

     /**
     * Ends the current sale and returns the total amount including VAT of the sale
     *
     * @return The total price of the sale including VAT.
     */
    public float endSale() {
        return sale.endSale();
    }

     /**
     * Processes the payment made by the customer.
     * Creates a new instance of cashPayment and sets the amount to the amount paid by the customer,
     * updates the cash register,
     * creates a saleDTO from sale,
     * printer receives saleDTO and prints it out,
     * updates external systems with the data from saleDTO.
     *
     * @param amountPaid The amount of money paid by the customer.
     */
    public void makePayment(float amountPaid) {
        float totalPrice = sale.getRunningTotal();
        cashRegister.updateCashInRegister(totalPrice);

        CashPayment cashPayment = new CashPayment(amountPaid, totalPrice);
        sale.setCashPayment(cashPayment);

        SaleDTO saleDTO = sale.toDTO();
        printer.printReceipt(saleDTO);
        updateExternalSystems(saleDTO);
    }

    private void updateExternalSystems(SaleDTO saleDTO) {
        accountingRegistryHandler.updateAccountingRegistry(saleDTO);
        inventoryRegistryHandler.updateInventoryRegistry(saleDTO);
    }
    
     /**
     * Fetches item details from inventory registry 
     *
     * @param itemId The item identification 
     * @return Item details
     */
    public ItemDTO getItemDTOfromId(String itemId) {
        return inventoryRegistryHandler.fetchItemById(itemId);
    }
}
