import java.util.*;

public class TreePathProblems {

    // 二元樹節點
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    // ===== 1. 找出所有根到葉路徑 =====
    public static List<List<Integer>> allRootToLeafPaths(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        backtrack(root, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(TreeNode node, List<Integer> path, List<List<Integer>> result) {
        path.add(node.val);
        if (node.left == null && node.right == null) {
            result.add(new ArrayList<>(path));
        } else {
            if (node.left != null) backtrack(node.left, path, result);
            if (node.right != null) backtrack(node.right, path, result);
        }
        path.remove(path.size() - 1);
    }

    // ===== 2. 是否存在和為目標值的路徑 =====
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return root.val == targetSum;
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    // ===== 3. 找出根到葉路徑最大和 =====
    public static int maxRootToLeafSum(TreeNode root) {
        if (root == null) return 0;
        int leftSum = maxRootToLeafSum(root.left);
        int rightSum = maxRootToLeafSum(root.right);
        return root.val + Math.max(leftSum, rightSum);
    }

    // ===== 4. 計算樹的直徑（任意兩節點最大路徑和） =====
    static int maxPathSumGlobal;

    public static int maxPathSum(TreeNode root) {
        maxPathSumGlobal = Integer.MIN_VALUE;
        dfsMaxPath(root);
        return maxPathSumGlobal;
    }

    private static int dfsMaxPath(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(dfsMaxPath(node.left), 0);
        int right = Math.max(dfsMaxPath(node.right), 0);
        maxPathSumGlobal = Math.max(maxPathSumGlobal, node.val + left + right);
        return node.val + Math.max(left, right);
    }

    // ===== 測試主程式 =====
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(12);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(7);

        System.out.println("所有根到葉路徑: " + allRootToLeafPaths(root));
        System.out.println("是否存在路徑和為 22: " + hasPathSum(root, 22));
        System.out.println("根到葉最大和: " + maxRootToLeafSum(root));
        System.out.println("樹的最大路徑和（直徑）: " + maxPathSum(root));
    }
}

