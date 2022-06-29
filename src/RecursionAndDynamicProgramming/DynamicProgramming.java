package RecursionAndDynamicProgramming;

public class DynamicProgramming {
    public static void main (String[] args) {
        for (int i = 0; i <= 10; i++) {
            System.out.println("triple step of " + i + ": " + tripleStep(i));
        }
    }

    public static int tripleStep(int n) {
        int a = 0, b=0, c=1, d=1;
        for (int i=0; i <= n; i++) {
            d = a+b+c;
            a=b;
            b=c;
            c=d;
        }
        return d;
    }
}
