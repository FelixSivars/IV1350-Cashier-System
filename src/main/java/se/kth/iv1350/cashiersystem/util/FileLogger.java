package se.kth.iv1350.cashiersystem.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Handles logging of exceptions to a text file, which can then support error tracking.
 */
public class FileLogger implements Logger {
    private static final FileLogger FILE_LOGGER = new FileLogger();

    private PrintWriter logStream;
    static final String FILE_PATH = "exception_log.txt";

    /**
     * Returns the single instance of <code>FileLogger</code>.
     *
     * @return The singleton instance of <code>FileLogger</code>.
     */
    public static FileLogger getInstance() {
        return FILE_LOGGER;
    }

    /**
     * Creates a new instance of <code>FileLogger</code> and opens a connection to the log file.
     * If the file cannot be accessed or created, an error message is printed to the console.
     */
    public FileLogger() {
        try {
            logStream = new PrintWriter(new FileWriter(FILE_PATH, true), true);
            logStream.println("------------------ Program Started ------------------\n");
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
    @Override
    public void exceptionLog(Exception e) {
        String message =
                "Date and time: \t" + getTimestamp() + "\n" +
                "Exception: \t\t" + e.getClass().getSimpleName() + "\n" +
                "Cause: \t\t\t" + e.getCause().getClass().getSimpleName() + "\n" +
                "Message: \t\t" + e.getCause().getMessage() + "\n" +
                "\n" +
                getStackTrace(e) + "\n";

        logStream.println(message);
    }

    @Override
    public void log(String message) {
        logStream.println(message);
    }

    private String getTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }

    private String getStackTrace(Exception e) {
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}
