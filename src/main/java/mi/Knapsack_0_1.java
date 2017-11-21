package mi;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
Input
1
58
41
57 95 13 29 1 99 34 77 61 23 24 70 73 88 33 61 43 5 41 63 8 67 20 72 98 59 46 58 64 94 97 70 46 81 42 7 1 52 20 54 81 3 73 78 81 11 41 45 18 94 24 82 9 19 59 48 2 72
83 84 85 76 13 87 2 23 33 82 79 100 88 85 91 78 83 44 4 50 11 68 90 88 73 83 46 16 7 35 76 31 40 49 65 2 18 47 55 38 75 58 86 77 96 94 82 92 10 86 54 49 65 44 77 22 81 52

Output
223
 */
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
                dp1.putAll(dp2);
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
