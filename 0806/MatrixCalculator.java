public class MatrixCalculator {

    // 矩陣加法
    public static int[][] add(int[][] A, int[][] B) {
        int rows = A.length;
        int cols = A[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = A[i][j] + B[i][j];
            }
        }
        return result;
    }

    // 矩陣乘法
    public static int[][] multiply(int[][] A, int[][] B) {
        int rows = A.length;
        int cols = B[0].length;
        int common = A[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = 0; k < common; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return result;
    }

    // 矩陣轉置
    public static int[][] transpose(int[][] A) {
        int rows = A.length;
        int cols = A[0].length;
        int[][] result = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = A[i][j];
            }
        }
        return result;
    }

    // 找最大值
    public static int maxValue(int[][] A) {
        int max = A[0][0];
        for (int[] row : A) {
            for (int val : row) {
                if (val > max) max = val;
            }
        }
        return max;
    }

    // 找最小值
    public static int minValue(int[][] A) {
        int min = A[0][0];
        for (int[] row : A) {
            for (int val : row) {
                if (val < min) min = val;
            }
        }
        return min;
    }

    // 輔助：印出矩陣
    public static void printMatrix(int[][] A) {
        for (int[] row : A) {
            for (int val : row) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] A = {
            {1, 2, 3},
            {4, 5, 6}
        };

        int[][] B = {
            {7, 8, 9},
            {10, 11, 12}
        };

        int[][] C = {
            {1, 2},
            {3, 4},
            {5, 6}
        };

        System.out.println("矩陣 A:");
        printMatrix(A);

        System.out.println("\n矩陣 B:");
        printMatrix(B);

        System.out.println("\nA + B:");
        printMatrix(add(A, B));

        System.out.println("\nA × C:");
        printMatrix(multiply(A, C));

        System.out.println("\nA 的轉置:");
        printMatrix(transpose(A));

        System.out.println("\nA 的最大值: " + maxValue(A));
        System.out.println("A 的最小值: " + minValue(A));
    }
}
