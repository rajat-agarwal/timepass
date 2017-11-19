package mi;
/**
 * Created by rajat.agarwal on 03/02/17.
 */

public class Median_Of_Two_Sorted_Arrays {
    private static int getMedian(int[] arr1, int[] arr2) {
        int n = arr1.length - 1;
        int m = arr2.length - 1;

        for (int i = 0, j = 0; i <= n && j <= m; ) {
            int midVal1 = arr1[(i + n) / 2];
            int midVal2 = arr2[(j + m) / 2];
            if (midVal1 < midVal2) {
                i = n / 2;
                m = m / 2;
            } else if (midVal1 > midVal2) {
                n = n / 2;
                j = m / 2;
            } else {
                return midVal1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 7, 9};
        int[] arr2 = {2, 4, 6, 8, 10};

        System.out.println(getMedian(arr1, arr2));
    }
}
