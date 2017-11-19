package mi;

import lombok.AllArgsConstructor;

/**
 * Created by rajat.agarwal on 14/07/17.
 */
public class Cheapest_Consecutive_Movie_Tickets {

    @AllArgsConstructor
    private static class Temp {
        int min_cost_so_far;
        int index;
    }

    public static void main(String[] args) {

        int[][] layout = {
                {2, 3, 4, 5, 4, 3, 2, 5, 2, 3},
                {1, 4, 3, 2, 5, 4, 7, 6, 5, 3},
                {3, 2, 1, 1, -1, 2, 3, 4, 5, 5},
                {1, 6, 5, 4, 3, 5, 6, 4, 3, 4},
                {2, 3, 4, 5, 4, 3, 2, 5, 2, 3}
        };

        find_horizontal_consecutive(layout, 4);
    }

    private static void find_horizontal_consecutive(int[][] layout, int num_tickets) {
        int min_cost_so_far = Integer.MAX_VALUE;

        for (int i = 0; i < layout.length; i++) {
            int min_cost_curr = find_min_in_a_row(layout[i], num_tickets);

            min_cost_so_far = Math.min(min_cost_so_far, min_cost_curr);
            System.out.println(min_cost_curr);
        }

        System.out.println("Final selected mininum min_cost_so_far=" + min_cost_so_far);
    }

    private static int find_min_in_a_row(int[] row, int num_tickets) {
        Temp ret = find_first_consecutive_seats_in_a_row(row, num_tickets, 0);
        int running_cost = ret.min_cost_so_far;

        for (; ret.index < row.length; ret.index++) {
            if (row[ret.index] == -1) {
                Temp r = find_first_consecutive_seats_in_a_row(row, num_tickets, ret.index);
                ret.index = r.index;
                running_cost = r.min_cost_so_far;
            } else {
                running_cost = running_cost - row[ret.index - num_tickets] + row[ret.index];
            }
            ret.min_cost_so_far = Math.min(ret.min_cost_so_far, running_cost);
        }
        return ret.min_cost_so_far;
    }

    private static Temp find_first_consecutive_seats_in_a_row(int[] row, int num_tickets, int j) {
        int cost = 0;
        int i = 0;
        for (; i < num_tickets && j < row.length; j++) {
            if (row[j] == -1) { //seat not empty
                cost = i = 0;
            } else {
                cost += row[j];
                i++;
            }
        }

        return i != num_tickets ? new Temp(Integer.MAX_VALUE, j) : new Temp(cost, j);
    }
}
