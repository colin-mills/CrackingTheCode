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

    public boolean getBit(int n, int i) {
        //creates a bit at the ith location and then returns true if it is a 1
        return (n & (1 << i)) != 0;
    }

    public int setBit(int n, int i) {
        //if it was a zero it will flip to a 1
        return n | (1 << i);
    }

    public int clearBit(int n, int i) {
        //creates mask which will be all 1s except a 0 at the ith position
        int mask = ~(1 << i);
        return n & mask;
    }

}
