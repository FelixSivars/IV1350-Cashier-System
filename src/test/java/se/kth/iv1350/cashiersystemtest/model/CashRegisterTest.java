package se.kth.iv1350.cashiersystemtest.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.cashiersystem.controller.Controller;
import se.kth.iv1350.cashiersystem.integration.Printer;
import se.kth.iv1350.cashiersystem.integration.RegistryCreator;
import se.kth.iv1350.cashiersystem.model.CashRegister;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CashRegisterTest {
    private static CashRegister cashRegister;
    private static RegistryCreator registryCreator;
    private static Printer printer;
    private static Controller controller;

    @BeforeAll
    public static void setUp() {
        registryCreator = new RegistryCreator();
        printer = new Printer();
        controller = new Controller(registryCreator, printer);
    }

    @BeforeEach
    public void createNewCashRegister() {
        cashRegister = new CashRegister();
    }

    @AfterEach
    public void tearDown() {
        cashRegister = null;
    }

    @Test
    public void testCashRegisterInitialization() {
        assertEquals(0f, cashRegister.getCashInRegister(),
                "The balance in cash register should be initialized with the correct amount, 0.");
    }

    @Test
    public void testAddCashInRegister() {
        cashRegister.addCashInRegister(10);
        cashRegister.addCashInRegister(15);
        assertEquals(25, cashRegister.getCashInRegister(),
                "Amount in cash register should increase with the expected amount.");
    }
}



