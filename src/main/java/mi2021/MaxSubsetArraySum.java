package mi2021;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

// The below solutions work for all the input types including cases when none of the input element is +ive (all the elements of the array are -ive).
public class MaxSubsetArraySum {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<int[]> manyInputs = mi.utilities.IntArrayInputCreator.createIntArrays();
        for (int[] input : manyInputs) {
            int[] ret = maxSubArraySum(input, 0, input.length - 1);
            System.out.println("Left:" + ret[0] + " Right:" + ret[1] + " MaxSum=" + ret[2] + " ValAtLeft=" + input[ret[0]] + " ValAtRight=" + input[ret[1]]);

            ret = maxSubArrayOptimised(input);
            System.out.println("Left:" + ret[0] + " Right:" + ret[1] + " MaxSum=" + ret[2] + " ValAtLeft=" + input[ret[0]] + " ValAtRight=" + input[ret[1]]);
        }
    }

    private static int[] maxSubArraySum(final int[] A, int start, int end) {
        if (start == end) {
            return new int[]{start, end, A[start]};
        }
        int mid = start + (end - start) / 2;
        int[] maxFromLeft = maxSubArraySum(A, start, mid);
        int[] maxFromRight = maxSubArraySum(A, mid + 1, end);
        int[] maxIncludingMid = maxSumIncludingMid(A, start, mid, end);

        if (maxFromLeft[2] >= Math.max(maxFromRight[2], maxIncludingMid[2])) {
            return maxFromLeft;
        } else if (maxFromRight[2] > Math.max(maxFromLeft[2], maxIncludingMid[2])) {
            return maxFromRight;
        } else {
            return maxIncludingMid;
        }
    }

    private static int[] maxSumIncludingMid(final int[] A, int start, int mid, int end) {
        int leftSumSoFar = Integer.MIN_VALUE, currSum = 0;
        int ret[] = new int[]{-1, -1, Integer.MIN_VALUE};
        for (int i = mid; i >= start; i--) {
            currSum += A[i];
            if (currSum > leftSumSoFar) {
                leftSumSoFar = currSum;
                ret[0] = i;
            }
        }

        int rightSumSoFar = Integer.MIN_VALUE;
        currSum = 0;
        for (int i = mid + 1; i <= end; i++) {
            currSum += A[i];
            if (currSum > rightSumSoFar) {
                rightSumSoFar = currSum;
                ret[1] = i;
            }
        }
        ret[2] = leftSumSoFar + rightSumSoFar;
        return ret;
    }

    private static int[] maxSubArrayOptimised(final int[] A) {
        int[] ret = new int[]{-1, -1, Integer.MIN_VALUE};
        boolean newSubArray = true;
        int maxSumSoFar = Integer.MIN_VALUE, currSum = 0;
        for (int i = 0; i < A.length; i++) {
            currSum += A[i];
            if (currSum > maxSumSoFar) {
                maxSumSoFar = currSum;
                ret[1] = i;
                if (newSubArray) {
                    ret[0] = i;
                    newSubArray = false;
                }
            }

            if (currSum < 0) {
                currSum = 0;
                newSubArray = true;
            }
        }
        ret[2] = maxSumSoFar;
        return ret;
    }
}
