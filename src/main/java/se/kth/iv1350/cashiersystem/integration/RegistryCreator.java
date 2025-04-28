package se.kth.iv1350.cashiersystem.integration;

/**
 * Creates and provides access to external registries
 */
public class RegistryCreator {
    private final InventoryRegistryHandler inventoryRegistryHandler = new InventoryRegistryHandler();
    private final AccountingRegistryHandler accountingRegistryHandler = new AccountingRegistryHandler();

    /**
     * Provides access to the <code>InventoryRegistryHandler</code>.
     *
     * @return The instance of <code>InventoryRegistryHandler</code>
     */
    public InventoryRegistryHandler getInventoryRegistryHandler() {
        return inventoryRegistryHandler;
    }

    /**
     * Provides access to the <code>AccountingRegistryHandler</code>.
     *
     * @return The instance of <code>AccountingRegistryHandler</code>
     */
    public AccountingRegistryHandler getAccountingRegistryHandler() {
        return accountingRegistryHandler;
    }
}
