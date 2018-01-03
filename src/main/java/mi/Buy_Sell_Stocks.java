package mi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Buy_Sell_Stocks {
    private static List<String> findMaxProfit(int[] input) {
        List<String> res = new ArrayList<>();
        int profit = 0;
        int s = 0, e = 1;
        for (;e < input.length - 1;e++) {
            if (input[s] >= input[e]) {
                s++;
            } else if (input[e] > input[e + 1]) {
                res.add(new String(s + " " + e));
                s = e + 1;
                e++;
            }
        }

        if (e < input.length && input[e] > input[s])
            res.add(new String(s + " " + e));

        return res;
    }

    public static void main(String[] args) {
        int[] price = {23, 13, 25, 29, 33, 19, 34, 45, 65, 67};
        List<String> res = findMaxProfit(price);
        for (String s : res)
            System.out.print("(" + s + ") ");
        System.out.println();
    }
}
