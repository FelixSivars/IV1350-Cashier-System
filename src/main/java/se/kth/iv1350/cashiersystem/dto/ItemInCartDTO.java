package se.kth.iv1350.cashiersystem.dto;

/**
 * DTO for items in cart
 * This class is used to transfer item data between the integration layer and the model layer.
 * It includes details about <@link ItemDTO> and the <code>quantity<code/> 
 */
public record ItemInCartDTO(ItemDTO itemDTO, int quantity) {
    
    /**
    * Gets <@ItemDTO> from the cart
    *
    * @return <@ItemDTO> from the cart
    */
    public ItemDTO getItemDTO() {
        return itemDTO;
    }

    /**
    * Gets quantity of the item from the cart
    *
    * @return Quantity of the item in the cart
    */
    public int getQuantity() {
        return quantity;
    }
}
