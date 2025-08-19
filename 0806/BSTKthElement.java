import java.util.ArrayList;
import java.util.List;

public class BSTKthElement {

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

    // 刪除節點
    public static TreeNode delete(TreeNode root, int key) {
        if (root == null) return null;
        if (key < root.val) root.left = delete(root.left, key);
        else if (key > root.val) root.right = delete(root.right, key);
        else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            TreeNode minNode = findMinNode(root.right);
            root.val = minNode.val;
            root.right = delete(root.right, minNode.val);
        }
        return root;
    }

    private static TreeNode findMinNode(TreeNode root) {
        while (root.left != null) root = root.left;
        return root;
    }

    // 中序遍歷
    private static void inorder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    // ===== 第 k 小元素 =====
    public static int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        if (k <= 0 || k > list.size()) throw new IllegalArgumentException("k out of range");
        return list.get(k - 1);
    }

    // ===== 第 k 大元素 =====
    public static int kthLargest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        if (k <= 0 || k > list.size()) throw new IllegalArgumentException("k out of range");
        return list.get(list.size() - k);
    }

    // ===== 第 k 小到第 j 小的元素範圍 =====
    public static List<Integer> rangeKtoJ(TreeNode root, int k, int j) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        if (k <= 0 || j > list.size() || k > j) throw new IllegalArgumentException("Invalid range");
        return list.subList(k - 1, j);
    }

    // ===== 測試主程式 =====
    public static void main(String[] args) {
        int[] values = {10, 5, 15, 3, 7, 12, 18};
        TreeNode root = null;
        for (int val : values) root = insert(root, val);

        System.out.println("第 3 小元素: " + kthSmallest(root, 3));
        System.out.println("第 2 大元素: " + kthLargest(root, 2));
        System.out.println("第 2 小到第 5 小元素: " + rangeKtoJ(root, 2, 5));

        root = delete(root, 10);
        System.out.println("刪除 10 後第 3 小元素: " + kthSmallest(root, 3));
    }
}

