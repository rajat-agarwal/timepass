package mi;

public class N_Queens_On_Board {
    private static void printBoard(boolean[][] board) {
        for (boolean[] r : board) {
            for (boolean c : r) {
                System.out.print((c ? "1" : "0") + " ");
            }
            System.out.println();
        }
    }

    private static boolean isSafePlacement(boolean[][] board, int r, int c) {
        //Row will have only one such queen already
        //Also note that since we are placing queens starting from row=0, so we need to check only above for attacking queens.
        for (int i = 0; i < r; i++) {
            //check column
            if (board[i][c])
                return false;
        }
        //check upper left diagnol
        for (int i = r - 1, j = c - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j])
                return false;
        }
        //check upper right diagnol
        for (int i = r - 1, j = c + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j])
                return false;
        }
        return true;
    }

    private static boolean placeNQueens(boolean[][] board, int r, int numQueens) {
        if (numQueens == 0)
            return true;
        for (int c = 0; c < board[r].length; c++) {
            if (isSafePlacement(board, r, c)) {
                board[r][c] = true;

                if (placeNQueens(board, r + 1, numQueens - 1))
                    return true;

                board[r][c] = false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int N = 4;
        int queens = 4;
        boolean[][] board = new boolean[4][4];
        placeNQueens(board, 0, queens);
        printBoard(board);
    }
}
