package mi;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Knapsack_0_1 {
    private static int findMaxValue(int[] varr, int[] warr, int w){
        int maxVal = 0;
        Map<Integer, Integer> dp1 = new HashMap<>();
        Map<Integer, Integer> dp2 = new HashMap<>();
        for(int i = 0;i<warr.length;i++){
            if(warr[i] <= w){
                dp2.put(warr[i],varr[i]);
                maxVal = Math.max(maxVal, varr[i]);
                for (Map.Entry<Integer,Integer> e : dp1.entrySet()){
                    if (e.getKey()+warr[i] <= w){
                        dp2.put(e.getKey()+warr[i], e.getValue()+varr[i]);
                        maxVal = Math.max(maxVal, e.getValue()+varr[i]);
                    }
                }
                Map<Integer, Integer> temp = dp1;
                dp1 = dp2;
                dp2 = temp;
                dp2.clear();
            }
        }
        return maxVal;
    }

    // A utility function that returns maximum of two integers
    static int max(int a, int b) { return (a > b)? a : b; }

    // Returns the maximum value that can be put in a knapsack of capacity W
    static int knapSack(int W, int wt[], int val[], int n)
    {
        int i, w;
        int K[][] = new int[n+1][W+1];

        // Build table K[][] in bottom up manner
        for (i = 0; i <= n; i++)
        {
            for (w = 0; w <= W; w++)
            {
                if (i==0 || w==0)
                    K[i][w] = 0;
                else if (wt[i-1] <= w)
                    K[i][w] = max(val[i-1] + K[i-1][w-wt[i-1]],  K[i-1][w]);
                else
                    K[i][w] = K[i-1][w];
            }
        }

        return K[n][W];
    }

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int w = sc.nextInt();
            int[] values = new int[n];
            int[] weight = new int[n];
            for(int i=0;i<n;i++){
                values[i] = sc.nextInt();
            }
            for(int i=0;i<n;i++){
                weight[i] = sc.nextInt();
            }
            System.out.println(findMaxValue(values, weight, w));
            System.out.println(knapSack(w,weight, values, n));
        }
    }
}
