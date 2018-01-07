package mi;

import java.util.Map;

public class Min_Of_Max_Distance_Between_Cows {
    private static int[] getMinOfMaxDistance(int[] input, int s, int k) {
        int[] minDistance = {s, Integer.MAX_VALUE};
        for (int i = s; i < input.length - k && k > 0; i++) {
            int[] result = getMinOfMaxDistance(input, i + 1, k - 1);
            if (minDistance[1] > Math.max(result[1], input[result[0]] - input[i])){
                minDistance[1] = Math.max(result[1], input[result[0]] - input[i]);
                minDistance[0] = i;
            }
        }
        return minDistance;
    }

    public static void main(String[] args) {
        int[] input = {1, 4, 8, 9};
        int cows = 3;
        int[] result = getMinOfMaxDistance(input, 0, cows);
        System.out.println(result[0] + " " + result[1]);
    }
}
