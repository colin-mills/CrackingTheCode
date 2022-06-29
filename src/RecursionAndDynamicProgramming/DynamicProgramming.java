package RecursionAndDynamicProgramming;

public class DynamicProgramming {
    public static void main (String[] args) {
        for (int i = 0; i <= 100; i++) {
            System.out.println("triple step of " + i + ": " + tripleStep(i));
        }
    }

    /*
    8.1 Triple Step
    pg. 134
    determine the number of ways you can reach the nth step if you can take a step of size 1, 2, or 3
    TC: O(n)
    SC: O(1)
     */
    public static int tripleStep(int n) {
        //for values larger than about 36 int will overflow
        //could solve this with long, but that will overflow soon
        //BigInteger is probably best bet
        int a = 0, b=0, c=1, d=1;
        for (int i=1; i <= n; i++) {
            d = a+b+c;
            a=b;
            b=c;
            c=d;
        }
        return d;
    }

    /*
    8.2 Robot in a grid
    pg. 135
    determine the number of ways a robot can reach the bottom right of a grid, either choosing to go down or right,
    => there can be obstacles
    TC: O(r * c) | where r = number of rows, c = number of columns
    SC: O(r * c) | memo grid, can optimize to O(c) if you are clever with array mangaement
     */
}
