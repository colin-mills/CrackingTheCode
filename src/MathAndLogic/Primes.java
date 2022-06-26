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
}
