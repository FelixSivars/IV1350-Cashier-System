package se.kth.iv1350.cashiersystem.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CashPaymentTest {

    @Test
    public void testCalculateChange() throws InsufficientPaymentException {
        float amountPaid = 100.0f;
        float totalPrice = 75.5f;
        CashPayment cashPayment = new CashPayment(amountPaid, totalPrice);

        float expectedChange = amountPaid - totalPrice;
        assertEquals(expectedChange, cashPayment.getChange(),
                "Change should be the difference between amount paid and total price");
    }

    @Test
    public void testGetAmountPaid() throws InsufficientPaymentException {
        float amountPaid = 200.0f;
        CashPayment cashPayment = new CashPayment(amountPaid, 150.0f);

        assertEquals(amountPaid, cashPayment.getAmountPaid(),
                "getAmountPaid should return the amount that was passed to the constructor");
    }

    @Test
    public void testExactPayment() throws InsufficientPaymentException {
        float amount = 50.0f;
        CashPayment cashPayment = new CashPayment(amount, amount);

        assertEquals(0.0f, cashPayment.getChange(),
                "Change should be zero when payment amount equals total price");
    }

    @Test
    public void testInsufficientPayment() {
        assertThrows(InsufficientPaymentException.class, () -> new CashPayment(10, 100),
                "The amount paid is insufficient and thus expecting an InsufficientPaymentException.");
    }

    @Test
    public void testSufficientPayment() {
        assertDoesNotThrow(() -> new CashPayment(100, 10),
                "The amount paid is sufficient and thus no exceptions are thrown.");
    }
}