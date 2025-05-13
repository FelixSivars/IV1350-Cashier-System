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
    public ItemDTO fetchItemById(String itemId) throws InvalidItemIdException, DatabaseFailureException {
        if (itemId.equals("ghj789"))
            throw new DatabaseFailureException("Could not fetch an item due to connection issues to the database.");

        if (itemCatalog.get(itemId) == null)
            throw new InvalidItemIdException("The item with the id '" + itemId + "' could not be found in the item catalog.");

        return itemCatalog.get(itemId);
    }

    private void fetchItems() {
        itemCatalog.put(
                "abc123",
                new ItemDTO.Builder()
                        .id("abc123")
                        .name("BigWheel Oatmeal")
                        .price(29.90f)
                        .description("BigWheel Oatmeal 500 g, whole grain oats, high fiber, gluten free")
                        .vatPercentage(6)
                        .build()
                );

        itemCatalog.put(
                "def456",
                new ItemDTO.Builder()
                        .id("def456")
                        .name("YouGoGo Blueberry")
                        .price(14.90f)
                        .description("YouGoGo Blueberry 240 g, low sugar yogurt, blueberry flavour")
                        .vatPercentage(6)
                        .build()
        );
    }

    /**
     * Updates the external inventory registry with data from the completed sale.
     *
     * @param saleDTO The {@link SaleDTO} used for updating inventory.
     */
    public void updateInventoryRegistry(SaleDTO saleDTO) {
        //TODO: Updates external inventory registry.
    }
}
