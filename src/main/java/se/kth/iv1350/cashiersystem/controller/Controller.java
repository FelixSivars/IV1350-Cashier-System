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
    private RegistryCreator registryCreator;
    private Printer printer;
    private InventoryRegistryHandler inventoryRegistry;
    private AccountingRegistryHandler accountingRegistry;
    private CashRegister cashRegister = new CashRegister();
    private Sale sale;

    public Controller(RegistryCreator registryCreator, Printer printer) {
        this.registryCreator = registryCreator;
        this.printer = printer;
        this.inventoryRegistry = registryCreator.getInventoryRegistry();
        this.accountingRegistry = registryCreator.getAccountingRegistry();
    }

    public void startSale() {
        this.sale = new Sale();
    }

    public float getRunningTotal() {
        return sale.getRunningTotal();
    }

    public float getVatTotal() {
        return sale.getVatTotal();
    }

    public void scanItem(String itemId, int quantity) {
        if (inventoryRegistry.isValidItemId(itemId)) {
            if (sale.isItemInSale(itemId)) {
                sale.updateQuantity(itemId, quantity);
            } else {
                ItemDTO itemDTO = inventoryRegistry.fetchItemById(itemId);
                Item item = itemDTO.toItem();
                sale.addItem(item, quantity);
            }
        }
        // ERROR
    }

    public float endSale() {
        return sale.endSale();
    }

    public void makePayment(float amountPaid) {
        float totalPrice = sale.getRunningTotal();
        cashRegister.updateCashInRegister(totalPrice);
        CashPayment cashPayment = new CashPayment(amountPaid, totalPrice);
        sale.setCashPayment(cashPayment);

        SaleDTO saleDTO = sale.toDTO();
        //ReceiptDTO receiptDTO = new ReceiptDTO(saleDTO, ite)
    }

}
