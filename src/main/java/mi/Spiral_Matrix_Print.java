package mi;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajat.agarwal on 22/07/17.
 */
public class Spiral_Matrix_Print {
    private static int[][] getInputMatrix(int N, int M) {
        int[][] matrix = new int[N][M];
        int val = 10;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = val++;
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        return matrix;
    }

    private static List<Integer> printSpiralSimplified(int[][] matrix) {
        List<Integer> output = new ArrayList<>();

        if (matrix == null || matrix.length == 0) {
            return output;
        }

        int rowStart = 0, colStart = 0, rowEnd = matrix.length - 1, colEnd = matrix[rowStart].length - 1;
        while (rowStart <= rowEnd && colStart <= colEnd) {

            for (int c = colStart; c <= colEnd; c++) {
                output.add(matrix[rowStart][c]);
            }
            rowStart++;

            for (int r = rowStart; r <= rowEnd; r++) {
                output.add(matrix[r][colEnd]);
            }
            colEnd--;

            if (rowStart <= rowEnd) {
                for (int c = colEnd; c >= colStart; c--) {
                    output.add(matrix[rowEnd][c]);
                }
            }
            rowEnd--;

            if (colStart <= colEnd) {
                for (int r = rowEnd; r >= rowStart; r--) {
                    output.add(matrix[r][colStart]);
                }
            }
            colStart++;
        }

        return output;
    }

    private static List<Integer> printSpiralComplicated(int[][] matrix) {
        List<Integer> output = new ArrayList<>();

        if (matrix == null || matrix.length == 0) {
            return output;
        }

        for (int layer = 0; layer <= (matrix.length - 1) / 2 && layer <= (matrix[0].length - 1) / 2; layer++) {

            int lastRowIdx = matrix.length - 1 - layer;
            int lastColIdx = matrix[layer].length - 1 - layer;

            for (int currCol = layer; currCol < lastColIdx; currCol++) {
                output.add(matrix[layer][currCol]);
            }

            for (int currRow = layer; currRow <= lastRowIdx; currRow++) {
                output.add(matrix[currRow][lastColIdx]);
            }

            if (layer != lastRowIdx) {
                for (int currCol = lastColIdx - 1; currCol > layer; currCol--) {
                    output.add(matrix[lastRowIdx][currCol]);
                }
            }

            if (layer != lastColIdx) {
                for (int currRow = lastRowIdx; currRow > layer; currRow--) {
                    output.add(matrix[currRow][layer]);
                }
            }
        }
        return output;
    }

    public static void main(String[] args) {
        int[][] matrix = getInputMatrix(2, 2);
        List<Integer> output1 = printSpiralComplicated(matrix);
        List<Integer> output2 = printSpiralSimplified(matrix);

        System.out.println(output2);
        System.out.println(output1.equals(output2));
    }
}
