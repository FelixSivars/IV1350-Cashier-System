package se.kth.iv1350.cashiersystem.integration;

/**
 * Creates and provides access to external registries
 */
public class RegistryCreator {
    private final InventoryRegistryHandler inventoryRegistryHandler = new InventoryRegistryHandler();
    private final AccountingRegistryHandler accountingRegistryHandler = new AccountingRegistryHandler();

    /**
     * Provides access to the InventoryRegistryHandler.
     *
     * @return The instance of InventoryRegistryHandler
     */
    public InventoryRegistryHandler getInventoryRegistryHandler() {
        return inventoryRegistryHandler;
    }

    /**
     * Provides access to the AccountingRegistryHandler.
     *
     * @return The instance of AccountingRegistryHandler
     */
    public AccountingRegistryHandler getAccountingRegistryHandler() {
        return accountingRegistryHandler;
    }
}
