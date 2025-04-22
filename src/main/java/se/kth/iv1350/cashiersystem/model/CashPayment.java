package se.kth.iv1350.cashiersystem.model;

public class CashPayment {
    private float amountPaid;
    private float totalPrice;
    private float change;

    public CashPayment(float amountPaid, float totalPrice) {
        this.amountPaid = amountPaid;
        this.totalPrice = totalPrice;

        calculateChange();
    }

    public void calculateChange() {
        change = amountPaid - totalPrice;
    }

    public float getChange() {
        return change;
    }

    public float getAmountPaid() {
        return amountPaid;
    }

    public float getTotalPrice() {
        return totalPrice;
    }
}
