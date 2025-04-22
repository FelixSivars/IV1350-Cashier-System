package se.kth.iv1350.cashiersystem.model;


public class CashRegister {
    private float cashInRegister;

    public CashRegister() {
    }

    public void updateCashInRegister(float totalPrice) {
        cashInRegister += totalPrice;
    }
}
