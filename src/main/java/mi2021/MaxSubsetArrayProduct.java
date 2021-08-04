package mi2021;

public class MaxSubsetArrayProduct {
    public static void main(String[] args) {
        int[] input = {-1, -3000, -10, 0, 60, -3, -30};
        System.out.println(findMaxProductSubarray(input));
        System.out.println(findMaxProductSubarray2Paas(input));
    }

    private static long findMaxProductSubarray(int[] input) {
        long minProduct, maxProduct, productSoFar;
        minProduct = maxProduct = productSoFar = input[0];
        int startIds = 0, endIdx = 0;

        for (int i = 1; i < input.length; i++) {
            if (input[i] == 0) {
                minProduct = maxProduct = 0;
                continue;
            }

            if (input[i] < 0) {
                long t = maxProduct;
                maxProduct = minProduct;
                minProduct = t;
            }

            maxProduct = Math.max(input[i], maxProduct * input[i]);
            minProduct = Math.min(input[i], minProduct * input[i]);

            if (maxProduct > productSoFar){
                productSoFar = maxProduct;
                endIdx = i;
            }
        }
        return productSoFar;
    }

    private static long findMaxProductSubarray2Paas(int[] input){
        //Traverse left to right and find max product
        //Traverse right to left and find max product
        // Max of the above two is the final answer
        //The max product will not cross "0" value boundary

        int maxProductLeftToRight = 1;
        int currProduct = 1;
        for (int i=0;i<input.length;i++){
            if (input[i] == 0){
                currProduct = 1;
                continue;
            }
            currProduct *= input[i];
            maxProductLeftToRight = Math.max(maxProductLeftToRight, currProduct);
        }

        int maxproductRightToLeft = 1;
        currProduct = 1;
        for (int i=input.length-1;i>=0;i--){
            if (input[i] == 0){
                currProduct = 1;
                continue;
            }
            currProduct *= input[i];
            maxproductRightToLeft = Math.max(maxproductRightToLeft, currProduct);
        }

        return Math.max(maxProductLeftToRight, maxproductRightToLeft);
    }
}
