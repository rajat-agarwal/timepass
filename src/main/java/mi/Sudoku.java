package mi;

import java.util.*;

public class Sudoku {
    static Set[] rows;
    static Set[] columns;
    static Set[] smallGrid;

    private static void initialize(int[][] input, int n) {
        rows = new Set[n];
        columns = new Set[n];
        smallGrid = new Set[n];

        for (int r = 0; r < n; r++) {
            rows[r] = new HashSet<Integer>(n);
            columns[r] = new HashSet<Integer>(n);
        }

        for (int r = 0; r < n; r += 3)
            for (int c = 0; c < n; c += 3)
                smallGrid[r + c / 3] = new HashSet<>(n);

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (input[r][c] != 0) {
                    rows[r].add(input[r][c]);
                    columns[c].add(input[r][c]);
                    smallGrid[(r / 3) * 3 + c / 3].add(input[r][c]);
                }
            }
        }
    }

    private static void printInput(int[][] input, int n) {
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                System.out.print(input[r][c] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int n = 9;
        int[][] input = {
                {3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0},
        };

        printInput(input, n);

        initialize(input, n);
        System.out.println(solve(input, n, 0, 0));
        printInput(input, n);
    }

    private static boolean validPlacement(int r, int c, int val) {
        return !rows[r].contains(val)
                && !columns[c].contains(val)
                && !smallGrid[(r / 3) * 3 + c / 3].contains(val);
    }

    private static void setValues(int[][] input, int r, int c, int val) {
        input[r][c] = val;
        rows[r].add(val);
        columns[c].add(val);
        smallGrid[(r / 3) * 3 + c / 3].add(val);
    }

    private static void unsetValues(int[][] input, int r, int c, int val) {
        input[r][c] = 0;
        rows[r].remove(val);
        columns[c].remove(val);
        smallGrid[(r / 3) * 3 + c / 3].remove(val);
    }

    private static boolean solve(int[][] input, int n, int row, int col) {
        for (int r = row; r < n; r++, col = 0) {
            for (int c = col; c < n; c++) {
                if (input[r][c] == 0) {
                    for (int val = 1; val <= 9; val++) {
                        if (validPlacement(r, c, val)) {
                            setValues(input, r, c, val);
                            if (solve(input, n, r, c + 1))
                                return true;
                            else
                                unsetValues(input, r, c, val);
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
