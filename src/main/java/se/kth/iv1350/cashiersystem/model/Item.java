package se.kth.iv1350.cashiersystem.model;

/**
 * Represents an item in the store.
 * Contains information about the item's ID, name, price, description, VAT percentage, and quantity.
 */
public class Item {
    private String id;
    private String name;
    private float price;
    private String description;
    private int vatPercentage;
    private int quantity;

    /**
     * Constructor for the Item class.
     * Initializes the item with the provided ID, name, price, description, VAT percentage,
     * and sets the default quantity to 1.
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
     * Gets the name of the item.
     *
     * @return The name of the item.
     */
    public String getName() {
        return name;
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
}
