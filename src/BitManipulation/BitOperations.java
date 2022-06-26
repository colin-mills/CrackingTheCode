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

}
