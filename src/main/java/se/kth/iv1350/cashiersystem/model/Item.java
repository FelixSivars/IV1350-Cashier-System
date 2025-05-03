package se.kth.iv1350.cashiersystem.model;

import se.kth.iv1350.cashiersystem.dto.ItemDTO;

/**
 * Represents an item in the store.
 * Contains information about the item's identification, name, price, description, VAT percentage, and quantity.
 */
public class Item {
    private final String id;
    private final String name;
    private final float price;
    private final String description;
    private final int vatPercentage;
    private int quantity;

    /**
     * Constructor for the <code>Item</code> class.
     * Initializes the item with the provided <code>id</code>, <code>name</code>, <code>price</code>,
     * <code>description</code>, <code>vatPercentage</code>,
     * and sets the default <code>quantity</code> to 1.
     *
     * @param id            The item identification code.
     * @param name          The name of the item.
     * @param price         The price of a single unit of the item.
     * @param description   A description of the item.
     * @param vatPercentage The VAT percentage applied to the item.
     */
    public Item(String id, String name, float price, String description, int vatPercentage) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.vatPercentage = vatPercentage;
        this.quantity = 1;
    }

    /**
     * Gets the item identification.
     *
     * @return The item identification.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the VAT percentage of the item.
     *
     * @return The VAT percentage of the item.
     */
    public int getVatPercentage() {
        return vatPercentage;
    }

    /**
     * Gets the quantity of the item.
     *
     * @return The quantity of the item.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Gets the price of the item.
     *
     * @return The price of the item.
     */
    public float getPrice() {
        return price;
    }

    /**
     * Sets quantity for the item.
     *
     * @param quantity The quantity to set the quantity for the item to.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Adds the specified amount to the current quantity of the item.
     *
     * @param quantity The number of items to add.
     */
    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    /**
     * Converts the current item to a {@link ItemDTO} object for data transfer.
     *
     * @return A {@link ItemDTO} containing the item details.
     */
    public ItemDTO toDTO() {
        return new ItemDTO(id, name, price, description, vatPercentage);
    }
}
