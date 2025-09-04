// 檔名：LC34_SearchRange_DelaySpan.java
import java.util.*;

public class LC34_SearchRange_DelaySpan {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();      // 序列長度
        int target = sc.nextInt(); // 要搜尋的延誤等級
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();
        sc.close();

        int[] range = searchRange(nums, target);
        System.out.println(range[0] + " " + range[1]);
    }

    // 返回 target 的首尾索引，找不到回 [-1,-1]
    public static int[] searchRange(int[] nums, int target) {
        int left = lowerBound(nums, target);
        int right = lowerBound(nums, target + 1) - 1; // 右邊界
        if (left <= right) {
            return new int[]{left, right};
        } else {
            return new int[]{-1, -1};
        }
    }

    // 找第一個 >= target 的索引
    public static int lowerBound(int[] nums, int target) {
        int l = 0, r = nums.length; // r = length，方便處理不存在
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < target) l = mid + 1;
            else r = mid;
        }
        return l;
    }
}
