package mi;

/*
https://web.archive.org/web/20100330183043/http://nlindblad.org/2007/04/04/write-your-own-square-root-function
Given a number, find its square root
 */
public class SQRT_Of_A_Number {
    public static void main(String[] args) {
        int num = 16;
        double firstFactor = num / 2; // take any random number less than num
        for (int i = 0; i < 5; i++) { //run this loop few times. More iterations will bring ans closer to actual sqrt
            double otherFactor = num / firstFactor;
            firstFactor = (firstFactor + otherFactor) / 2.0;
        }
        System.out.println(firstFactor);
    }
}
