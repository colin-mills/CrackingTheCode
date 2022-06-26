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

    public int clearBits(int n, int lsb, int msb) {
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

    /*
    5.2 Binary to String
    pg. 116
    Convert a real number between 0-1 to a String in its decimal representation
    TC: O(1)
    SC: O(1)
     */
    public String getFractionalBinaryAsString (double real) {
        if (real < 0 || real >= 1) return "ERROR";
        char[] charArr = new char[33];
        charArr[0] = '.';
        double fraction = .5;
        for(int i=1; i < charArr.length; i++) {
            if (real > fraction) {
                real -= fraction;
                charArr[i] = '1';
                //exit if 0
                if (real == 0) return new String(charArr);
            } else charArr[i] = '0';

            fraction /= 2;
        }
        return "ERROR";
    }

    /*
    5.3 Flip bit to win
    pg. 116
    Find the longest streak of 1s in a binary representation by flipping any 1 bit
    TC: O(1)
    SC: O(1)
     */
    public int maxBitStreakWithFlip(int n) {
        //edge case bc the algorithm will add 1 to 32 and return 33
        if (n == Integer.MAX_VALUE) return 32;
        //we can early exit if this is the case bc no ones
        if (n == 0) return 1;

        int max = 1, currentStreak = 0, lastStreak = 0;
        while (n != 0) {
            if ((n & 1) == 1) currentStreak++;
            else {
                //we hit a 0
                //current max or the union of the last two streaks
                max = Math.max(max, currentStreak + lastStreak + 1);
                lastStreak = currentStreak;
                currentStreak = 0;
            }
            n >>= 1;
        }
        //one last check in case the 32nd number was a 1
        return Math.max(max, currentStreak + lastStreak + 1);
    }
}
