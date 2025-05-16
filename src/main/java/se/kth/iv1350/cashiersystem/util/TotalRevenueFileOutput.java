package se.kth.iv1350.cashiersystem.util;

import se.kth.iv1350.cashiersystem.model.Observer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TotalRevenueFileOutput implements Observer {
    private static String FILE_PATH = "revenue_log.txt";
    private PrintWriter logStream;

    public TotalRevenueFileOutput() {
        try {
            logStream = new PrintWriter(new FileWriter(FILE_PATH, true), true);
            logStream.println("------------------ Program Started ------------------\n");
        } catch (IOException ioe) {
            System.out.println("oops something went wrong.");
            ioe.printStackTrace();
        }
    }

    @Override
    public void updateRevenue(float balance) {
        logStream.printf(getTimestamp() + "\nTotal Revenue: %.2f SEK\n\n", balance);
    }

    private String getTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
}
