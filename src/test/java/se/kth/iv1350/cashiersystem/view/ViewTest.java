package se.kth.iv1350.cashiersystem.view;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.cashiersystem.controller.Controller;
import se.kth.iv1350.cashiersystem.dto.ItemDTO;
import se.kth.iv1350.cashiersystem.dto.ItemInCartDTO;
import se.kth.iv1350.cashiersystem.dto.SaleDTO;
import se.kth.iv1350.cashiersystem.integration.PrinterService;
import se.kth.iv1350.cashiersystem.integration.RegistryCreator;

public class ViewTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void testTotalRevenueViewDisplaysCorrectAmount() {
        TotalRevenueView revenueView = new TotalRevenueView();
        revenueView.updateRevenue(123.45f);

        String output = outContent.toString();
        assertTrue(output.contains("123.45"), "Expected output to contain the revenue amount.");
    }

    @Test
    void testReceiptPrintIncludesItemNameAndTotal() {
        PrinterService printer = PrinterService.getInstance();

        SaleDTO saleDTO = new SaleDTO.Builder()
            .dateTime(LocalDateTime.now())
            .runningTotal(99.99f)
            .vatTotal(9.99f)
            .amountPaid(100.00f)
            .change(0.01f)
            .itemsInCartDTO(List.of(
                new ItemInCartDTO(
                    new ItemDTO("abc123", "BigWheel Oatmeal", 29.90f, "Oats", 6), 1
                )
            ))
            .build();

        printer.printReceipt(saleDTO);
        String output = outContent.toString();

        assertTrue(output.contains("BigWheel Oatmeal"), "Expected item name in receipt.");
        assertTrue(output.contains("99.99"), "Expected total price in receipt.");
    }

    @Test
    void testViewDisplaysStartSaleMessage() {
        RegistryCreator registryCreator = RegistryCreator.getInstance();
        PrinterService printer = PrinterService.getInstance();
        Controller controller = new Controller(registryCreator, printer);

        View view = new View(controller);
        view.simulationRun();  // assuming it prints “A new sale has been started.”

        String output = outContent.toString();
        assertTrue(output.contains("A new sale has been started"), "Expected start sale message.");
    }




    
}
