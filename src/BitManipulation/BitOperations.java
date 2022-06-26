package BitManipulation;

public class BitOperations {
    /*
    Bit operators overview
    & - bitwise and
    | - bitwise or
    ^ - bitwise Xor (exclusive or)
    ~ - bitwise not
    >> - right shift signed (arithmetic)
    >>> - right shift unsigned (logical)
    << - left shift
     */

    public static boolean getBit(int n, int i) {
        //creates a bit at the ith location and then returns true if it is a 1
        return (n & (1 << i)) != 0;
    }

    public static int setBit(int n, int i) {
        //if it was a zero it will flip to a 1
        return n | (1 << i);
    }

    public static int clearBit(int n, int i) {
        //creates mask which will be all 1s except a 0 at the ith position
        int mask = ~(1 << i);
        return n & mask;
    }

    public static int clearMSBtoI(int n, int i) {
        //mask will be all 1s from 0 to i-1
        int mask = (1 << i) - 1;
        return n & mask;
    }

    //basically we clear the bit, and then set it if value is 1
    public static int updateBit(int n, int i, boolean val) {
        int value = val ? 1 : 0;
        //shift value over to the ith position
        value = (value << i);
        //create mask to clear that bit
        int mask = ~(1 << i);
        return (n & mask) | value;
    }
}
