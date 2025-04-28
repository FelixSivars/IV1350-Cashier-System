package se.kth.iv1350.cashiersystem.model;

import se.kth.iv1350.cashiersystem.dto.SaleDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

/**
* Represents an ongoing sale process.
*/
public class Sale {
    private Collection<Item> itemsInCart = new ArrayList<>();
    private CashPayment cashPayment;
    private float runningTotal;
    private float vatTotal;

    //Not in seminar 3.
    private float discount = 0;

    /**
    * Checks if the item is already in the current sale.
    *
    * @param itemId The identification of the item.
    * @return <code>true</code> if the item is already in the sale, otherwise <code>false</code>.
    */
    public boolean isItemInSale(String itemId) {
        Item item = findItemInCart(itemId);
        return item != null;
    }

    /**
    * Sets the cash payment for the sale.
    *
    * @param cashPayment The <code>CashPayment</code> object containing payment details.
    */
    public void setCashPayment(CashPayment cashPayment) {
        this.cashPayment = cashPayment;
    }

    /**
    * Updates the quantity of an existing item in the sale.
    * Updates the running total and the total VAT after updating the quantity.
    *
    * @param itemId   The identification of the item.
    * @param quantity The quantity to add.
    */
    public void updateQuantity(String itemId, int quantity) {
        Item item = findItemInCart(itemId);
        item.addQuantity(quantity);
        calculateRunningTotal();
        calculateVatTotal();
    }

    private Item findItemInCart(String itemId) {
        for (Item item : itemsInCart) {
            if (item.getId().equals(itemId)) {
                return item;
            }
        }
        return null;
    }


    /**
    * Adds a new item to the sale.
    * Updates the running total and the total VAT after adding.
    *
    * @param item     The <code>Item</code> to add.
    * @param quantity The <code>quantity</code> of the item to add which is default 1.
    */
    public void addItem(Item item, int quantity) {
        item.setQuantity(quantity);
        itemsInCart.add(item);
        calculateRunningTotal();
        calculateVatTotal();
    }

    /**
    * Gets the current running total of the sale.
    *
    * @return The running total of the sale.
    */
    public float getRunningTotal() {
        return runningTotal;
    }

    /**
    * Gets the total VAT of the sale.
    *
    * @return The total VAT of the sale.
    *
    public float getVatTotal() {
        return vatTotal;
    }

    private void calculateRunningTotal() {
        runningTotal = 0f;
        for (Item item : itemsInCart) {
            runningTotal += item.getPrice() * item.getQuantity();
        }
    }

    private void calculateVatTotal() {
        vatTotal = 0f;
        for (Item item : itemsInCart) {
            vatTotal += calculateVat(item) * item.getQuantity();
        }
    }

    //PER ITEM
    private float calculateVat(Item item) {
        float price = item.getPrice();
        int vatPercentage = item.getVatPercentage();
        return price * ((float) vatPercentage / 100);
    }

    /**
    * Ends the sale.
    *
    * @return The total price of the sale.
    */
    public float endSale() {
        return runningTotal;
    }
    
    /**
     * Converts the current sale to a {@link SaleDTO} object for data transfer.
     *
     * @return A {@link SaleDTO} containing the sale details.
     */
    public SaleDTO toDTO() {
        LocalDateTime dateTime = LocalDateTime.now();
        //Map<String, Integer> itemQuantityMap = getItemQuantityMap();
        return new SaleDTO(dateTime, runningTotal, vatTotal, cashPayment.getAmountPaid(), cashPayment.getChange(), discount, itemsInCart);
    }

    //NOT IN USE FOR SEMINAR 3
    /*
    private Map<String, Integer> getItemQuantityMap() {
        Map<String, Integer> itemQuantityMap = new HashMap<>();
        for (Item item : itemsInCart) {
            itemQuantityMap.put(item.getId(), item.getQuantity());
        }
        return itemQuantityMap;
    }
    */
}

