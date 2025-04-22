package se.kth.iv1350.cashiersystem.dto;

import se.kth.iv1350.cashiersystem.model.Item;

public record ItemDTO(String id, String name, float price, String description, int vatPercentage) {
    public Item toItem() {
        return new Item(id, name, price, description, vatPercentage);
    }

}
