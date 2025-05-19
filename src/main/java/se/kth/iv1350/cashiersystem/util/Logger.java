package se.kth.iv1350.cashiersystem.util;

/**
 * Interface for logging messages and exceptions.
 */
public interface Logger {
    public void log(String message);
    public void exceptionLog(Exception e);

}
