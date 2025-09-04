import java.util.*;

public class LC39_CombinationSum_PPE {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), target = sc.nextInt();
        int[] candidates = new int[n];
        for (int i = 0; i < n; i++) candidates[i] = sc.nextInt();
        sc.close();

        Arrays.sort(candidates); // 遞增排序方便剪枝
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        for (List<Integer> comb : res) {
            for (int x : comb) System.out.print(x + " ");
            System.out.println();
        }
    }

    // 回溯函式
    static void backtrack(int[] nums, int remain, int start, List<Integer> path, List<List<Integer>> res) {
        if (remain == 0) { // 剩餘為 0 收錄組合
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (nums[i] > remain) break; // 剪枝
            path.add(nums[i]);
            backtrack(nums, remain - nums[i], i, path, res); // 可重複使用 i
            path.remove(path.size() - 1); // 回溯
        }
    }
}
