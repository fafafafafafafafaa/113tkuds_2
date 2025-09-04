// 檔名：LC33_SearchRotated_RentHot.java
import java.util.*;

public class LC33_SearchRotated_RentHot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();      // 陣列長度
        int target = sc.nextInt(); // 要搜尋的目標
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();
        sc.close();

        int index = search(nums, target);
        System.out.println(index);
    }

    // 返回 target 在旋轉陣列中的索引，找不到回 -1
    public static int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) return mid; // 找到目標
            if (nums[l] <= nums[mid]) { // 左半段有序
                if (nums[l] <= target && target < nums[mid]) {
                    r = mid - 1; // 目標在左半段
                } else {
                    l = mid + 1; // 目標在右半段
                }
            } else { // 右半段有序
                if (nums[mid] < target && target <= nums[r]) {
                    l = mid + 1; // 目標在右半段
                } else {
                    r = mid - 1; // 目標在左半段
                }
            }
        }
        return -1; // 找不到
    }
}
