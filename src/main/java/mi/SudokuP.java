package mi;

public class SudokuP {
    public static void main(String[] args) {
        int[][] arr = {{3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}};
//        int[][] arr = {{3, 0, 6},
//                {5, 2, 0},
//                {0, 8, 7},
//        };
        System.out.println(sudoku(arr));
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr.length; j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }

    static boolean sudoku(int[][] arr) {
        return sudokuUtil(arr, 0, 0);
    }

    static boolean sudokuUtil(int[][] arr, int i, int j) {
        if(i>=arr.length) return true;
        if (arr[i][j] != 0) {
            if (j == arr[0].length - 1) {
                return sudokuUtil(arr, i + 1, 0);
            } else {
                return sudokuUtil(arr, i, j + 1);
            }
        }

        for (int k = 1; k <= 9; k++) {
            if (isSafe(arr, i, j, k)) {
                arr[i][j] = k;

                boolean ret;
                if (j == arr[0].length - 1) {
                    ret = sudokuUtil(arr, i + 1, 0);
                } else {
                    ret = sudokuUtil(arr, i, j + 1);
                }

                if (ret) {
                    return true;
                } else {
                    arr[i][j] = 0;
                }

            }
        }
        return false;
    }

    static boolean isSafe(int[][] arr, int i, int j, int num) {
        //row
        for (int k = 0; k < arr.length; k++) {
            if (arr[i][k] == num) {
                return false;
            }
        }
        //col
        for (int k = 0; k < arr.length; k++) {
            if (arr[k][j] == num) {
                return false;
            }
        }
        //box
        int boxStartRow = i - i % 3;
        int boxStartCol = j - j % 3;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (arr[boxStartRow + r][boxStartCol + c] == num) {
                    return false;
                }
            }
        }
        return true;
    }
}
