package mi2021;

/*
https://www.geeksforgeeks.org/find-subarray-with-given-sum/
Find a subarray with sum equal to given sum

-> Another cool solution is to increment all the elements of the array by the absolute value of the lowest negative number. This will essentially make every element as positive and hence the Sliding window technique used in D:\Git\rAjAT\timepass\src\main\java\mi2021\FindSubarrayWithGivenSum.java can be used in O(N). More details here https://www.geeksforgeeks.org/find-subarray-with-given-sum-with-negatives-allowed-in-constant-space/

->Another approach could be to precompute sum so far at every index. If the diff b/w two such index sum so far equal to the given sum, we have found the array
*/
public class FindSubarrayWithGivenSum {
    public static void main(String[] args) {
        findSubarraySumWithNonNegatives();
    }

    public static void findSubarraySumWithNonNegatives() {
        int input[] = {15, 2, 4, 8, 9, 5, 10, 23};
        int givenSum = 23;
        int sumSoFar = 0;

        for (int start = 0, end = 0; end < input.length; end++) {
            sumSoFar += input[end];

            /* since all the elements are positive, if the running sum so far becomes more than required,
            We need to remove elements from start. See this as a sliding window always holding elements with sum <= required sum
             */
            while (sumSoFar > givenSum) {
                sumSoFar -= input[start];
                start++;
            }

            if (sumSoFar == givenSum) {
                System.out.println("start idx = " + start + " end Idx = " + end);
                return; // continue if all the subarrays are required
            }
        }
    }

    public static void findSubarraySumWithNegatives() {
        // Another cool solution is to increment all the elements of the array by the absolute value of the lowest negative number. This will essentially make every element as positive and hence the
        // Sliding window technique used in D:\Git\rAjAT\timepass\src\main\java\mi2021\FindSubarrayWithGivenSum.java can be used in O(N). More details here https://www.geeksforgeeks.org/find-subarray-with-given-sum-with-negatives-allowed-in-constant-space/
    }
}
