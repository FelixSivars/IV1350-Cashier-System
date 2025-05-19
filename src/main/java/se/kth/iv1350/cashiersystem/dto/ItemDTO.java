package se.kth.iv1350.cashiersystem.dto;

import se.kth.iv1350.cashiersystem.model.Item;

/**
 * DTO for item details.
 * This class is used to transfer item data between the integration layer and the model layer.
 * It includes details about an item such as item identification, price of each item, item description and VAT percentage of an item.
 */
public record ItemDTO(String id, String name, float price, String description, int vatPercentage) {

    /**
     * A builder class for creating an {@link ItemDTO} instance using the Builder design pattern.
     */
    public static final class Builder {
        private String id;
        private String name;
        private float price;
        private String description;
        private int vatPercentage;

        /**
         * Sets the item ID.
         *
         * @param id The identifier of the item.
         * @return This builder instance for method chaining.
         */
        public Builder id(String id) {
            this.id = id;
            return this;
        }

        /**
         * Sets the item name.
         *
         * @param name The name of the item.
         * @return This builder instance for method chaining.
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the item price.
         *
         * @param price The price of the item.
         * @return This builder instance for method chaining.
         */
        public Builder price(float price) {
            this.price = price;
            return this;
        }

        /**
         * Sets the item description.
         *
         * @param description The description of the item.
         * @return This builder instance for method chaining.
         */
        public Builder description(String description) {
            this.description = description;
            return this;
        }

        /**
         * Sets the item VAT percentage.
         *
         * @param vatPercentage The identifier of the item.
         * @return This builder instance for method chaining.
         */
        public Builder vatPercentage(int vatPercentage) {
            this.vatPercentage = vatPercentage;
            return this;
        }

        /**
         * Builds a new {@link ItemDTO} instance using the current builder values.
         *
         * @return The built {@code ItemDTO}.
         */
        public ItemDTO build() {
            return new ItemDTO(id, name, price, description, vatPercentage);
        }
    }

    /**
     * Builds a new {@link ItemDTO} instance using the current builder values.
     *
     * @return A {@code ItemDTO} object.
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
