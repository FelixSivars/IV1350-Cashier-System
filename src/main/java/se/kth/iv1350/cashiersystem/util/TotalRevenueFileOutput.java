package se.kth.iv1350.cashiersystem.util;

import se.kth.iv1350.cashiersystem.model.RevenueObserver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A {@link RevenueObserver} implementation that logs total revenue updates to a file.
 * Each update appends a timestamped revenue entry to revenue_log.txt}.
 */
public class TotalRevenueFileOutput implements RevenueObserver {
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
     * Appends the current total revenue to the log file with a timestamp.
     *
     * @param balance The current total revenue to log.
     */
    @Override
    public void updateRevenue(float balance) {
        logStream.printf(getTimestamp() + "\nTotal Revenue: %.2f SEK\n\n", balance);
    }

    private String getTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
}
