package se.kth.iv1350.cashiersystem.model;

/**
 * Interface for observers that want to be notified when revenue is updated.
 */
public interface RevenueObserver {
    void updateRevenue(float balance);
}
