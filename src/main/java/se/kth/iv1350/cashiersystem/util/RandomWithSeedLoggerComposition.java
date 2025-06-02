package se.kth.iv1350.cashiersystem.util;

import java.util.Random;

/**
 * An example of adapting java.util.Random using composition.
 * Adds logging behavior by wrapping an internal Random object.
 */
public class RandomWithSeedLoggerComposition {
    private final Random random;

    /**
     * Creates a new adapter using composition.
     */
    public RandomWithSeedLoggerComposition() {
        this.random = new Random();
    }

    /**
     * Generates a random integer and prints a log message.
     *
     * @param bound The upper bound (exclusive) of the generated number.
     * @return A random integer less than bound.
     */
    public int nextInt(int bound) {
        int result = random.nextInt(bound);
        System.out.println("[Composition] Generated number: " + result);
        return result;
    }
}

