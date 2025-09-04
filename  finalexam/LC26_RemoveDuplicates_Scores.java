// 檔名：LC26_RemoveDuplicates_Scores.java
import java.util.*;

public class LC26_RemoveDuplicates_Scores {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 讀入成績單長度
        int[] scores = new int[n];
        for (int i = 0; i < n; i++) {
            scores[i] = sc.nextInt();
        }
        sc.close();

        int newLen = removeDuplicates(scores);

        // 輸出壓縮後長度
        System.out.println(newLen);
        // 輸出前段結果
        for (int i = 0; i < newLen; i++) {
            System.out.print(scores[i]);
            if (i != newLen - 1) System.out.print(" ");
        }
    }

    // 原地去重，返回新長度
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int write = 1; // 可寫位置指標
        for (int i = 1; i < nums.length; i++) {
            // 只有當前值與前一保留值不同才寫入
            if (nums[i] != nums[write - 1]) {
                nums[write] = nums[i];
                write++;
            }
        }
        return write;
    }
}
