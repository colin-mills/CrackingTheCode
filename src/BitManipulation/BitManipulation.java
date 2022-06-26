package BitManipulation;

public class BitManipulation {
    /*
    5.1 Insertion
    pg. 115
    insert m into n at j -> i bit positions | where the LSB is 0
    TC: O(1)
    SC: O(1)
     */
    public int insertM (int n, int m, int lsb, int msb) {
        //error code
        if (lsb < 0 || msb > 32 || lsb > msb) return -1;
        n = clearBits(n, lsb, msb);
        //shift m over the appropriate amount
        m <<= lsb;
        return m | n;
    }

    private int clearBits(int n, int lsb, int msb) {
        //find out how big the mask should be
        int size = msb - lsb + 1;
        //make the mask
        //example: 2^3 = 8 decimal, 1000
        // 1000 - 1 = 0111 which is a 3 digit mask
        int mask = (int) Math.pow(2, size) -1;
        //shift the mask over the start at the LSB and not it so the values from LSB to MSB, inclusive are 0;
        mask = ~(mask << lsb);
        return n & mask;
    }
}
