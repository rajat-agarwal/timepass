package mi;

/**
 * Created by rajat.agarwal on 03/02/17.
 */

public class Median_Of_Two_Sorted_Arrays {
    private static int getMedianIterative(int[] arr1, int[] arr2) {
        int n = arr1.length - 1;
        int m = arr2.length - 1;

        for (int i = 0, j = 0; i <= n && j <= m; ) {
            int midVal1 = arr1[(i + n) / 2];
            int midVal2 = arr2[(j + m) / 2];
            if (midVal1 < midVal2) {
                i = i + (n - i) / 2;
                m = j + (m - j) / 2;
            } else if (midVal1 > midVal2) {
                n = i + (n - i) / 2;
                j = j + (m - j) / 2;
            } else {
                return midVal1;
            }
        }
        return 0;
    }

    private static double MO2(double a, double b) {
        return (a + b) / 2.0;
    }

    private static double MO3(double a, double b, double c) {
        return a + b + c - Math.min(a, Math.min(b, c)) - Math.max(a, Math.max(b, c));
    }

    private static double MO4(double a, double b, double c, double d) {
        double min = Math.min(Math.min(a, b), Math.min(c, d));
        double max = Math.max(Math.max(a, b), Math.max(c, d));
        return (a + b + c + d - min - max) / 2;
    }

    private static double MOArray(int[] arr) {
        if (arr == null || arr.length == 0)
            return -1;
        else if (arr.length % 2 == 0)
            return (arr[arr.length / 2] + arr[(arr.length / 2) - 1]) / 2.0;
        else
            return arr[arr.length / 2];
    }

    //Using Further Optimization illustrated in http://www.drdobbs.com/parallel/finding-the-median-of-two-sorted-arrays/240169222?pgno=2
    private static double getMedian(int[] small, int ss, int se, int[] big, int bs, int be) {
        int smallLen = se - ss + 1;
        int bigLen = be - bs + 1;
        int sm = ss + (se - ss) / 2;
        int bm = bs + (be - bs) / 2;

        // http://www.geeksforgeeks.org/median-of-two-sorted-arrays-of-different-sizes/
        // If smaller array is empty, return median from second array
        if (smallLen == 0)
            return MOArray(big);

        if (smallLen == 1) {
            // Case 1: If the larger array also has one element,
            // simply call MO2()
            if (bigLen == 1)
                return MO2(small[ss], big[bs]);

            // Case 2: If the larger array has even number of element,
            // then median will be one of the following 3 elements
            // ... The middle two elements of larger array
            // ... The only element of smaller array
            if (bigLen % 2 == 0)
                return MO3(small[ss], big[bm], big[bm + 1]);

                // Case 3: If the larger array has odd number of elements,
                // then consider the middle 3 elements of larger array and
                // the only element of smaller array. Take few examples
                // like following
                // A = {9}, B[] = {5, 8, 10, 20, 30} and
                // A[] = {1}, B[] = {5, 8, 10, 20, 30}
            else
                return MO2(MO3(small[ss], big[bm - 1], big[bm + 1]), big[bm]);
        }

        // If the smaller array has two elements
        if (smallLen == 2) {
            // Case 4: If the larger array also has two elements,
            // simply call MO4()
            if (bigLen == 2)
                return MO4(small[ss], small[se], big[bs], big[be]);

            // Case 5: If the larger array has odd number of elements,
            // then median will be one of the following 3 elements
            // 1. Middle element of larger array
            // 2. Max of first element of smaller array and element
            //    just before the middle in bigger array
            // 3. Min of second element of smaller array and element
            //    just after the middle in bigger array

            if (bigLen % 2 != 0)
                return MO3(big[bm], Math.max(small[ss], big[bm - 1]), Math.min(small[se], big[bm + 1]));

                // Case 6: If the larger array has even number of elements,
                // then median will be one of the following 4 elements
                // 1) & 2) The middle two elements of larger array
                // 3) Max of first element of smal-50, -21, -10ler array and element
                //    just before the first middle element in bigger array
                // 4. Min of second element of smaller array and element
                //    just after the second middle in bigger array
            else
                return MO4(big[bm], big[bm + 1], Math.max(small[ss], big[bm - 1]), Math.min(small[se], big[bm + 2]));
        }

        //smaller of the two range differences should be dropped
        int smLeft = sm - ss;
        int smRight = se - sm;
        int bmLeft = bm - bs;
        int bmRight = be - bm;

        if (small[sm] < big[bm]) {
            sm = ss + Math.min(smLeft, bmRight);
            bm = be - Math.min(smLeft, bmRight);
            return getMedian(small, sm, se, big, bs, bm);
        } else if (small[sm] > big[bm]) {
            bm = bs + Math.min(bmLeft, smRight);
            sm = se - Math.min(bmLeft, smRight);
            return getMedian(small, ss, sm, big, bm, be);
        } else {
            return big[bm];
        }
    }

    public static void main(String[] args) {
//        int[] big = {-50, -41, -40, -19, 5, 21, 28};
//        int[] small = {-50, -21, -10};
//        int[] big = {-43, -25, -18, -15, -10, 9, 39, 40};
//        int[] small = {-2};
//        int[] big = {-41, -33, -24, -21, -20, 27, 48};
//        int[] small = {-9};
        int[] big = {-28, -27, 38, 49};
        int[] small = {-34, 9, 36, 48};
        if (big.length < small.length) {
            int[] temp = big;
            big = small;
            small = temp;
        }

        //Using Further Optimization illustrated in http://www.drdobbs.com/parallel/finding-the-median-of-two-sorted-arrays/240169222?pgno=2
        if (big.length - small.length < 4) {
            System.out.println(getMedian(small, 0, small.length - 1, big, 0, big.length - 1));
        } else {
            int bigStart = (big.length - 1) / 2;
            int bigEnd = big.length % 2 == 0 ? bigStart + 1 : bigStart;
            int lookupRange = (small.length + 1) / 2;
            System.out.println(getMedian(small, 0, small.length - 1, big, bigStart - lookupRange, bigEnd + lookupRange));
        }
//        System.out.println(getMedianIterative(arr1, arr2));
    }
}
