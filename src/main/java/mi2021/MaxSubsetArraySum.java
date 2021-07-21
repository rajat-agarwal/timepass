package mi2021;

public class MaxSubsetArraySum {
    public static void main(String[] args) {
        //int[] input = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        int[] input = new int[] {1, 2, 3, 4, -10};
        System.out.println(maxSubArray(input));
    }

    private static int maxSubArray(final int[] A) {
        int maxSumSoFar = Integer.MIN_VALUE, currSum = 0;
        for(int a : A){
            currSum += a;
            maxSumSoFar = Math.max(maxSumSoFar, currSum);
            if(currSum <=0){
                currSum = 0;
            }

        }
        return maxSumSoFar;
    }
}
