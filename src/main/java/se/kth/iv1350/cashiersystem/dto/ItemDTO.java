package se.kth.iv1350.cashiersystem.dto;

import se.kth.iv1350.cashiersystem.model.Item;

/**
 * DTO for item details.
 * This class is used to transfer item data between the integration layer and the model layer.
 * It includes details about an item such as item identification, price of each item, item description and VAT percentage of an item.
 */
public record ItemDTO(String id, String name, float price, String description, int vatPercentage) {

    /**
     * Converts this <code>ItemDTO</code> to an {@link Item}.
     *
     * @return A new {@link Item} object containing the same data as this DTO.
     */
    public Item toItem() {
        return new Item(id, name, price, description, vatPercentage);
    }

    @Override
    public String toString() {
        return "Item ID: " + id + "\n" +
                "Item name: " + name + "\n" +
                "Item cost: " + price + " SEK \n" +
                "VAT: " + vatPercentage + " % \n" +
                "Item description: " + description;
    }
}
