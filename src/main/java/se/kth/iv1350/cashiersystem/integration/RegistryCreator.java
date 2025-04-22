package se.kth.iv1350.cashiersystem.integration;

/**
 *
 */
public class RegistryCreator {
    private final InventoryRegistryHandler inventoryRegistry = new InventoryRegistryHandler();
    private final AccountingRegistryHandler accountingRegistry = new AccountingRegistryHandler();

    public InventoryRegistryHandler getInventoryRegistry() {
        return inventoryRegistry;
    }

    public AccountingRegistryHandler getAccountingRegistry() {
        return accountingRegistry;
    }
}
