package RecursionAndDynamicProgramming;

public class Recursion {
    public static int minProduct(int a, int b) {
        int bigger = a < b ? b : a;
        int smaller = a < b ? a : b;
        return minProductHelper(smaller, bigger);
    }

    private static int minProductHelper(int smaller, int bigger) {
        if (smaller == 0) return 0;
        if (smaller == 1) return bigger;

        int s = smaller >> 1; //divide by 2
        int halfProd = minProductHelper(s, bigger);
        return (smaller % 2 == 0) ?
                halfProd + halfProd : //if even
                halfProd + halfProd + bigger; //if odd
    }
}
