package se.kth.iv1350.cashiersystem.integration;

import se.kth.iv1350.cashiersystem.dto.ItemDTO;
import se.kth.iv1350.cashiersystem.dto.SaleDTO;

import java.util.HashMap;
import java.util.Map;

public class InventoryRegistryHandler {
    private final Map<String, ItemDTO> itemCatalog = new HashMap<>();

    public InventoryRegistryHandler() {
        fetchItems();
    }

    public ItemDTO fetchItemById(String itemId) {
        return itemCatalog.get(itemId);
    }

    public boolean isValidItemId(String itemId) {
        return fetchItemById(itemId) != null;
    }

    private void fetchItems() {
        itemCatalog.put("abc123", new ItemDTO("abc123", "BigWheel Oatmeal", 29.90f,
                "BigWheel Oatmeal 500 g, whole grain oats, high fiber , gluten free", 6));
        itemCatalog.put("def456", new ItemDTO("def456", "YouGoGo Blueberry", 14.90f,
                "YouGoGo Blueberry 240 g ,low sugar youghurt, blueberry flavour", 6));
    }

    public void updateInventoryRegistry(SaleDTO saleDTO) {
        // DO SOMETHING :)
    }
}
