package se.kth.iv1350.cashiersystem.util;

import java.util.Random;

/**
 * An example of adapting java.util.Random using inheritance.
 * Adds logging behavior by extending the original Random class.
 */
public class RandomWithSeedLoggerInheritance extends Random {

    /**
     * Generates a random integer and prints a log message.
     *
     * @param bound The upper bound (exclusive) of the generated number.
     * @return A random integer less than bound.
     */
    @Override
    public int nextInt(int bound) {
        int result = super.nextInt(bound);
        System.out.println("[Inheritance] Generated number: " + result);
        return result;
    }
}

