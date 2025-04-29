package se.kth.iv1350.cashiersystem.dto;

public record ItemInCartDTO(ItemDTO itemDTO, int quantity) {
    public ItemDTO getItemDTO() {
        return itemDTO;
    }

    public int getQuantity() {
        return quantity;
    }
}
