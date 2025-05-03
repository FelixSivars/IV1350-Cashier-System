package se.kth.iv1350.cashiersystem.dto;

/**
 * DTO for items in cart
 * This class is used to transfer item data between the integration layer and the model layer.
 * It includes details about {@link ItemDTO} and the quantity
 */
public record ItemInCartDTO(ItemDTO itemDTO, int quantity) {
}
