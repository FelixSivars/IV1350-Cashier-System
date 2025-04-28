package se.kth.iv1350.cashiersystem.dto;

import se.kth.iv1350.cashiersystem.model.Item;

/**
 * DTO for item details.
 * This class is used to transfer item data between the integration layer and the model layer.
 * It includes details about an item such as <code>id</code>, <code>name</code>, <code>price</code>, 
 * <code>description</code>, and <code>vatPercentage</code>.
 */
public record ItemDTO(String id, String name, float price, String description, int vatPercentage) {

    /**
     * Converts this <code>ItemDTO</code> to an <code>Item</code>.
     *
     * @return A new <code>Item</code> object containing the same data as this DTO.
     */
    public Item toItem() {
        return new Item(id, name, price, description, vatPercentage);
    }
}
