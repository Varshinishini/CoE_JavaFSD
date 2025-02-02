package task_10;

public class MatrixMultiplier implements Runnable {
	private int[][] matrixA, matrixB, result;
    private int row, col;

    public MatrixMultiplier(int[][] matrixA, int[][] matrixB, int[][] result, int row, int col) {
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.result = result;
        this.row = row;
        this.col = col;
    }

    @Override
    public void run() {
        int sum = 0;
        for (int k = 0; k < matrixA[0].length; k++) {
            sum += matrixA[row][k] * matrixB[k][col];
        }
        result[row][col] = sum;
    }

}
