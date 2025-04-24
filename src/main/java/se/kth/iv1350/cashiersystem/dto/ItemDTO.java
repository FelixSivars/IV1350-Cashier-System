package se.kth.iv1350.cashiersystem.dto;

import se.kth.iv1350.cashiersystem.model.Item;

/**
 * DTO for item details
 * This class is used to transfer item data between the integration layer and the model layer.
 * It includes details about an item such as ID, name, price, description, and VAT percentage.
 */
public record ItemDTO(String id, String name, float price, String description, int vatPercentage) {
/**
* Converts this ItemDTO to an Item object
*
* @return A new Item object containing the same data as this DTO.
*/
    public Item toItem() {
        return new Item(id, name, price, description, vatPercentage);
    }

}
