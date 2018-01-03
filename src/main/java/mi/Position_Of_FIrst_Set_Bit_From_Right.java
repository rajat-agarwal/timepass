package mi;

public class Position_Of_FIrst_Set_Bit_From_Right {
    public static void main(String[] args) {
        for (int n = 1; n < 20; n++) {
            int negation = -n;
            int r = n & ~(n - 1);
            System.out.print(Integer.toBinaryString(n) + " ");
            System.out.println((int) (Math.log(r) / Math.log(2)) + 1);
        }
    }
}
