package MathAndLogic;

import java.util.Arrays;

public class Primes {
    public static boolean[] getListOfPrimes(int max) {
        //Generate primes using the Sieve of Eratosthenes method
        boolean[] flags = new boolean[max+1];
        int count = 0;

        Arrays.fill(flags,true);
        int prime = 2; //first prime

        while (prime <= Math.sqrt(max)) {
            //cross off remaining multiples of prime
            crossOff(flags, prime);
            //find next value that is true
            prime = getNextPrime(flags, prime);
        }
        return flags;
    }

    private static void crossOff(boolean[] flags, int prime) {
        //cross off remaining multiples of prime
        //we can start with prime * prime bc if we have a k * prime , where k < prime
        //this value already would have been crossed up
        for (int i = prime * prime; i < flags.length; i += prime) {
            flags[i] = false;
        }
    }

    private static int getNextPrime(boolean[] flags, int prime) {
        int next = prime + 1;
        while (next < flags.length && !flags[next]) next++;

        return next;
    }
}
