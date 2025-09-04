// 檔名：LC04_Median_QuakeFeeds.java
import java.util.*;

public class LC04_Median_QuakeFeeds {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 第一列長度
        int m = sc.nextInt(); // 第二列長度

        double[] A = new double[n];
        double[] B = new double[m];

        for (int i = 0; i < n; i++) A[i] = sc.nextDouble();
        for (int i = 0; i < m; i++) B[i] = sc.nextDouble();

        sc.close();

        System.out.printf("%.1f\n", findMedianSortedArrays(A, B));
    }

    // 計算兩個排序數列的中位數
    public static double findMedianSortedArrays(double[] A, double[] B) {
        // 保證 A 是較短的數列
        if (A.length > B.length) {
            return findMedianSortedArrays(B, A);
        }

        int n = A.length, m = B.length;
        int totalLeft = (n + m + 1) / 2; // 左半邊總數量

        int left = 0, right = n;
        while (left <= right) {
            int i = (left + right) / 2; // A 左邊取多少
            int j = totalLeft - i;      // B 左邊取多少

            // 邊界處理（超過就設為 ±∞）
            double Aleft  = (i == 0) ? Double.NEGATIVE_INFINITY : A[i - 1];
            double Aright = (i == n) ? Double.POSITIVE_INFINITY : A[i];
            double Bleft  = (j == 0) ? Double.NEGATIVE_INFINITY : B[j - 1];
            double Bright = (j == m) ? Double.POSITIVE_INFINITY : B[j];

            // 確認切割正確
            if (Aleft <= Bright && Bleft <= Aright) {
                if ((n + m) % 2 == 1) {
                    // 總長度奇數 → 左半最大值
                    return Math.max(Aleft, Bleft);
                } else {
                    // 總長度偶數 → 左半最大值與右半最小值的平均
                    return (Math.max(Aleft, Bleft) + Math.min(Aright, Bright)) / 2.0;
                }
            } else if (Aleft > Bright) {
                // A 左邊太多，往左縮
                right = i - 1;
            } else {
                // A 左邊太少，往右擴
                left = i + 1;
            }
        }

        throw new IllegalArgumentException("Invalid input data.");
    }
}
