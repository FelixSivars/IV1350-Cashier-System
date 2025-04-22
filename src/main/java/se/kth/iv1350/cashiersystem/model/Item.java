package se.kth.iv1350.cashiersystem.model;

public class Item {
    private String id;
    private String name;
    private float price;
    private String description;
    private int vatPercentage;
    private int quantity;

    //Default quANTITY 1
    public Item(String id, String name, float price, String description, int vatPercentage) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.vatPercentage = vatPercentage;
        this.quantity = 1;
    }

    public String getId() {
        return id;
    }

    public int getVatPercentage() {
        return vatPercentage;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }
}
