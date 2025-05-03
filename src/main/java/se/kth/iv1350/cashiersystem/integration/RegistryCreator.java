package se.kth.iv1350.cashiersystem.integration;

/**
 * Creates and provides access to external registries
 */
public class RegistryCreator {
    private final InventoryRegistryHandler inventoryRegistryHandler = new InventoryRegistryHandler();
    private final AccountingRegistryHandler accountingRegistryHandler = new AccountingRegistryHandler();

    /**
     * Provides access to the {@link InventoryRegistryHandler}.
     *
     * @return The instance of {@link InventoryRegistryHandler}
     */
    public InventoryRegistryHandler getInventoryRegistryHandler() {
        return inventoryRegistryHandler;
    }

    /**
     * Provides access to the {@link AccountingRegistryHandler}.
     *
     * @return The instance of {@link AccountingRegistryHandler}.
     */
    public AccountingRegistryHandler getAccountingRegistryHandler() {
        return accountingRegistryHandler;
    }
}
