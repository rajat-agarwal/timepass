package mi2021;

public class RotateMatrixBy90 {
    public static void main(String[] args) {
        int[][] squareMatrix = new int[][] {{1, 2,},{3, 4}};
        rotateBy90(squareMatrix);
    }

    private static void rotateBy90(final int[][] squareMatrix){
        int N = squareMatrix.length;
        for (int r=0;r<N;r++){
            for (int c=0;c<N;c++){
                int temp = squareMatrix[r][c];
                squareMatrix[r][c] = squareMatrix[c][r];
                squareMatrix[c][r] = squareMatrix[N-1-r][N-1-c];
                squareMatrix[N-1-r][N-1-c] = squareMatrix[r][N-1-c];
                squareMatrix[r][N-1-c] = temp;
            }
            N--;
        }

    }
}
