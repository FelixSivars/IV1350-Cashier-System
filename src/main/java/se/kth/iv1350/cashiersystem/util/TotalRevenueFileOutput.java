package se.kth.iv1350.cashiersystem.util;

import se.kth.iv1350.cashiersystem.model.RevenueObserver;
import se.kth.iv1350.cashiersystem.model.TemplateRevenueObserver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Logs the total revenue of all sales to a file.
 * Extends {@link TemplateRevenueObserver} and defines file writing behavior.
 */
public class TotalRevenueFileOutput extends TemplateRevenueObserver {
    private static String FILE_PATH = "revenue_log.txt";
    private PrintWriter logStream;

    /**
     * Creates a new instance of <code>TotalRevenueFileOutput</code> and opens the log file for appending.
     * If the file does not exist, it will be created. A startup message is written at the beginning.
     */
    public TotalRevenueFileOutput() {
        try {
            logStream = new PrintWriter(new FileWriter(FILE_PATH, true), true);
            logStream.println("------------------ Program Started ------------------\n");
        } catch (IOException ioe) {
            System.out.println("oops something went wrong.");
            ioe.printStackTrace();
        }
    }

    /**
     * Writes the total income to a file named "revenue_log.txt".
     *
     * @throws IOException If the file could not be written to.
     */
    @Override
    protected void doShowTotalIncome() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            writer.printf(getTimestamp() + "\nTotal revenue: %.2f SEK%n", getTotalIncome());
        }
    }


    /**
     * Handles any exceptions that occur while trying to log revenue to a file.
     *
     * @param e The exception that occurred.
     */
    @Override
    protected void handleErrors(Exception e) {
        System.out.println("Failed to log revenue to file: " + e.getMessage());
    }

    private String getTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
}
