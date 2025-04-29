package se.kth.iv1350.cashiersystem.integration;

import se.kth.iv1350.cashiersystem.dto.SaleDTO;

/**
 * Responsible for handling the interaction with the external accounting registry system.
 */
public class AccountingRegistryHandler {

    /**
     * Updates the accounting registry with the details of a completed sale that is used for accounting.
     *
     * @param saleDTO The {@link SaleDTO} containing all information about the completed sale
     *                that is needed to update the accounting registry.
     */
    public void updateAccountingRegistry(SaleDTO saleDTO) {
        //unknown to the assignment what it does exactly with the sale data to update the accounting registry
    }
}
