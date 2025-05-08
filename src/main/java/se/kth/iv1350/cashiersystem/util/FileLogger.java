package se.kth.iv1350.cashiersystem.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * Handles logging of exceptions to a text file, which can then support error tracking.
 */
public class FileLogger {
    private PrintWriter logStream;
    static final String FILE_PATH = "log.txt";

    /**
     * Creates a new instance of <code>FileLogger</code> and opens a connection to the log file.
     * If the file cannot be accessed or created, an error message is printed to the console.
     */
    public FileLogger() {
        try {
            logStream = new PrintWriter(new FileWriter(FILE_PATH), true);
        } catch (IOException ioe) {
            System.out.println("oops something went wrong.");
            ioe.printStackTrace();
        }
    }

    /**
     * Logs details that includes timestamp, exception type, cause of exception and message to the log file.
     *
     * @param e The exception written down in the log file.
     */
    public void log(Exception e) {
        logStream.println("Date and time: \t" + getTimestamp());
        logStream.println("Exception: \t\t" + e.getClass().getSimpleName());
        logStream.println("Cause: \t\t\t" + e.getCause().getClass().getSimpleName());
        logStream.println("Message: \t\t" + e.getCause().getMessage());
        logStream.println(" ");
    }

    private String getTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
}
