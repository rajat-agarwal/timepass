package mi;

import lombok.AllArgsConstructor;

/**
 * Created by rajat.agarwal on 14/07/17.
 */
public class Cheapest_Consecutive_Movie_Tickets {

    public static void main(String[] args) {

        int[][] layout = {
                {2, 3, 4, 5, 4, 3, 2, 5, 2, 3},
                {1, 4, 3, 2, 5, 4, 7, 6, 5, 3},
                {3, 2, 1, 1, -1, 2, 3, 4, 5, 5},
                {1, 6, 5, 4, 3, 5, 6, 4, 3, 4},
                {2, 3, 4, 5, 4, 3, 2, 5, 2, 3}
        };

        findMinCostSeats(layout, 4);
    }

    private static int allocate(int[] row, int tickets) {
        int costSoFar = 0;
        int minCost = Integer.MAX_VALUE;
        int ticketsBooked = 0;
        for (int i = 0; i < row.length; i++) {
            if (ticketsBooked == tickets) {
                minCost = Math.min(minCost, costSoFar);
                costSoFar -= row[i - ticketsBooked];
                ticketsBooked--;
            }
            if (row[i] > 0) {
                costSoFar += row[i];
                ticketsBooked++;
            } else {
                ticketsBooked = 0;
                costSoFar = 0;
            }
        }
//        System.out.println(minCost);
        return minCost;
    }

    private static void findMinCostSeats(int[][] layout, int tickets) {
        int mincost = Integer.MAX_VALUE;
        for (int[] row : layout) {
            mincost = Math.min(mincost, allocate(row, tickets));
        }
        System.out.println(mincost);
    }
}
