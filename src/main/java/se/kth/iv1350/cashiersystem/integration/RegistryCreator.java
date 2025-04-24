package se.kth.iv1350.cashiersystem.integration;

/**
 * Creates and provides access to external registries
 */
public class RegistryCreator {
    private final InventoryRegistryHandler inventoryRegistry = new InventoryRegistryHandler();
    private final AccountingRegistryHandler accountingRegistry = new AccountingRegistryHandler();

    /**
     * Provides access to the InventoryRegistryHandler.
     * 
     * @return The instance of InventoryRegistryHandler
     */
    public InventoryRegistryHandler getInventoryRegistry() {
        return inventoryRegistry;
    }

    /**
     * Provides access to the AccountingRegistryHandler.
     * 
     * @return The instance of AccountingRegistryHandler
     */
    public AccountingRegistryHandler getAccountingRegistry() {
        return accountingRegistry;
    }
}
