package se.kth.iv1350.cashiersystem.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CashPaymentTest {

    @Test
    public void testCalculateChange() {
        float amountPaid = 100.0f;
        float totalPrice = 75.5f;
        CashPayment cashPayment = new CashPayment(amountPaid, totalPrice);

        float expectedChange = amountPaid - totalPrice;
        assertEquals(expectedChange, cashPayment.getChange(),
                "Change should be the difference between amount paid and total price");
    }

    @Test
    public void testGetAmountPaid() {
        float amountPaid = 200.0f;
        CashPayment cashPayment = new CashPayment(amountPaid, 150.0f);

        assertEquals(amountPaid, cashPayment.getAmountPaid(),
                "getAmountPaid should return the amount that was passed to the constructor");
    }

    @Test
    public void testExactPayment() {
        float amount = 50.0f;
        CashPayment cashPayment = new CashPayment(amount, amount);

        assertEquals(0.0f, cashPayment.getChange(),
                "Change should be zero when payment amount equals total price");
    }
}