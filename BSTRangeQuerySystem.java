import java.util.ArrayList;
import java.util.List;

public class BSTRangeQuerySystem {

    // BST 節點
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    // 插入節點
    public static TreeNode insert(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) root.left = insert(root.left, val);
        else root.right = insert(root.right, val);
        return root;
    }

    // ===== 範圍查詢 =====
    public static List<Integer> rangeQuery(TreeNode root, int min, int max) {
        List<Integer> result = new ArrayList<>();
        rangeQueryHelper(root, min, max, result);
        return result;
    }

    private static void rangeQueryHelper(TreeNode root, int min, int max, List<Integer> result) {
        if (root == null) return;
        if (root.val > min) rangeQueryHelper(root.left, min, max, result);
        if (root.val >= min && root.val <= max) result.add(root.val);
        if (root.val < max) rangeQueryHelper(root.right, min, max, result);
    }

    // ===== 範圍計數 =====
    public static int rangeCount(TreeNode root, int min, int max) {
        if (root == null) return 0;
        if (root.val < min) return rangeCount(root.right, min, max);
        if (root.val > max) return rangeCount(root.left, min, max);
        return 1 + rangeCount(root.left, min, max) + rangeCount(root.right, min, max);
    }

    // ===== 範圍總和 =====
    public static int rangeSum(TreeNode root, int min, int max) {
        if (root == null) return 0;
        if (root.val < min) return rangeSum(root.right, min, max);
        if (root.val > max) return rangeSum(root.left, min, max);
        return root.val + rangeSum(root.left, min, max) + rangeSum(root.right, min, max);
    }

    // ===== 最接近查詢 =====
    public static int closestValue(TreeNode root, int target) {
        TreeNode current = root;
        int closest = root.val;
        while (current != null) {
            if (Math.abs(current.val - target) < Math.abs(closest - target)) {
                closest = current.val;
            }
            if (target < current.val) current = current.left;
            else if (target > current.val) current = current.right;
            else break;
        }
        return closest;
    }

    // ===== 測試主程式 =====
    public static void main(String[] args) {
        int[] values = {10, 5, 15, 3, 7, 12, 18};
        TreeNode root = null;
        for (int val : values) root = insert(root, val);

        int min = 5, max = 15;
        System.out.println("範圍查詢 [" + min + ", " + max + "]: " + rangeQuery(root, min, max));
        System.out.println("範圍計數 [" + min + ", " + max + "]: " + rangeCount(root, min, max));
        System.out.println("範圍總和 [" + min + ", " + max + "]: " + rangeSum(root, min, max));
        int target = 13;
        System.out.println("最接近 " + target + " 的值: " + closestValue(root, target));
    }
}

