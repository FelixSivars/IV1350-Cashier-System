package se.kth.iv1350.cashiersystem.util;

public class RandomDemo {
    public static void main(String[] args) {
        RandomWithSeedLoggerInheritance inheritance = new RandomWithSeedLoggerInheritance();
        RandomWithSeedLoggerComposition composition = new RandomWithSeedLoggerComposition();

        System.out.println("Using inheritance:");
        inheritance.nextInt(100);

        System.out.println("\nUsing composition:");
        composition.nextInt(100);
    }
}

