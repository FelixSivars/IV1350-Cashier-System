package se.kth.iv1350.cashiersystem.integration;

import se.kth.iv1350.cashiersystem.dto.ItemDTO;
import se.kth.iv1350.cashiersystem.dto.SaleDTO;

import java.util.HashMap;
import java.util.Map;

/**
 * Handles communication with the external inventory registry.
 */
public class InventoryRegistryHandler {
    private final Map<String, ItemDTO> itemCatalog = new HashMap<>();

    /**
     * Creates a new instance of <code>InventoryRegistryHandler</code>.
     * Populates the item catalog by fetching available items.
     */
    public InventoryRegistryHandler() {
        fetchItems();
    }

    /**
     * Creates a new instance of <code>InventoryRegistryHandler</code>.
     * Populates the item catalog using its parameters. Used for test
     * cases.
     *
     * @param itemDTO The item that should be in the item catalog.
     */
    public InventoryRegistryHandler(ItemDTO itemDTO) {
        itemCatalog.put(itemDTO.id(), itemDTO);
    }

    /**
     * Retrieves an item from the catalog using its item ID.
     *
     * @param itemId The identification of the item to fetch.
     * @return The {@link ItemDTO} corresponding to the ID, or <code>null</code>if the item does not exist.
     */
    public ItemDTO fetchItemById(String itemId) {
        return itemCatalog.get(itemId);
    }

    /**
     * Checks whether the given item ID exists in the inventory registry.
     *
     * @param itemId The identification of the item to check.
     * @return <code>true</code> if the item exists, <code>false</code> otherwise.
     */
    public boolean isValidItemId(String itemId) {
        return fetchItemById(itemId) != null;
    }

    private void fetchItems() {
        itemCatalog.put("abc123", new ItemDTO("abc123", "BigWheel Oatmeal", 29.90f,
                "BigWheel Oatmeal 500 g, whole grain oats, high fiber, gluten free", 6));
        itemCatalog.put("def456", new ItemDTO("def456", "YouGoGo Blueberry", 14.90f,
                "YouGoGo Blueberry 240 g, low sugar yogurt, blueberry flavour", 6));
    }

    /**
     * Updates the external inventory registry with data from the completed sale.
     *
     * @param saleDTO The {@link SaleDTO} used for updating inventory.
     */
    public void updateInventoryRegistry(SaleDTO saleDTO) {
        //TODO: unknown to the assignment how it updates
    }
}
