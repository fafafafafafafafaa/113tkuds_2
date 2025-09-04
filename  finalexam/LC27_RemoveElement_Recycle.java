// 檔名：LC27_RemoveElement_Recycle.java
import java.util.*;

public class LC27_RemoveElement_Recycle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();      // 陣列長度
        int val = sc.nextInt();    // 指定要移除的值
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        sc.close();

        int newLen = removeElement(nums, val);

        // 輸出新長度
        System.out.println(newLen);
        // 輸出前段結果
        for (int i = 0; i < newLen; i++) {
            System.out.print(nums[i]);
            if (i != newLen - 1) System.out.print(" ");
        }
    }

    // 原地移除指定元素，返回新長度
    public static int removeElement(int[] nums, int val) {
        int write = 0; // 可寫位置指標
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) { // 只寫入非 val 元素
                nums[write] = nums[i];
                write++;
            }
        }
        return write;
    }
}
