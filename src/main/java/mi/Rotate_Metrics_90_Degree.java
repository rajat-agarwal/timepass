package mi;

import mi.utilities.AssortedMethods;

/**
 * Created by rajat.agarwal on 29/06/17.
 */
public class Rotate_Metrics_90_Degree {
    public static void main(String[] args) {
        int[][] input = AssortedMethods.randomMatrix(7, 7, 0, 9);

        printMetrics(input);
        System.out.println(" ---------------------");
//        rotate90degreeWithSpace(input);
        System.out.println(" ---------------------");
        rotate90degreeWithoutSpace(input);
    }

    private static void rotate90degreeWithoutSpace(int[][] input) {
        int N = input.length;
        for (int k = 0; k < N / 2; k++) {
            for (int i = k; i < N - k; i++) {
                int v = input[k][i]; // top left

                input[k][i] = input[N - 1 - i][k]; //bottom left

                input[N - 1 - i][k] = input[N - 1 - k][N - 1 - i]; //bottom right

                input[N - 1 - k][N - 1 - i] = input[i][N - 1 - k]; //top right

                input[i][N - 1 - k] = v;
            }
        }

        printMetrics(input);
    }

    private static void rotate90degreeWithSpace(int[][] input) {
        int rows = input.length;
        int cols = input[0].length;

        int[][] output = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                output[j][cols - 1 - i] = input[i][j];
            }
        }

        printMetrics(output);
    }

    private static void printMetrics(int[][] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                System.out.print("  " + input[i][j]);
            }
            System.out.println();
        }
    }
}
