import java.util.*;

public class BSTConversionAndBalance {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    // ===== 1. BST 轉排序雙向鏈結串列 =====
    static TreeNode prev = null;
    static TreeNode head = null;

    public static TreeNode bstToDoublyList(TreeNode root) {
        if (root == null) return null;
        prev = null;
        head = null;
        inOrderConvert(root);
        return head;
    }

    private static void inOrderConvert(TreeNode node) {
        if (node == null) return;
        inOrderConvert(node.left);
        if (prev == null) {
            head = node;
        } else {
            prev.right = node;
            node.left = prev;
        }
        prev = node;
        inOrderConvert(node.right);
    }

    // ===== 2. 排序陣列轉平衡 BST =====
    public static TreeNode sortedArrayToBST(int[] nums) {
        return buildBST(nums, 0, nums.length - 1);
    }

    private static TreeNode buildBST(int[] nums, int start, int end) {
        if (start > end) return null;
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildBST(nums, start, mid - 1);
        root.right = buildBST(nums, mid + 1, end);
        return root;
    }

    // ===== 3. 檢查是否平衡並計算平衡因子 =====
    public static boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    private static int checkHeight(TreeNode node) {
        if (node == null) return 0;
        int left = checkHeight(node.left);
        int right = checkHeight(node.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) return -1;
        return Math.max(left, right) + 1;
    }

    // ===== 4. 將 BST 節點值改為大於等於自身的總和 =====
    static int sum = 0;
    public static void bstToGreaterSumTree(TreeNode root) {
        sum = 0;
        reverseInOrder(root);
    }

    private static void reverseInOrder(TreeNode node) {
        if (node == null) return;
        reverseInOrder(node.right);
        sum += node.val;
        node.val = sum;
        reverseInOrder(node.left);
    }

    // ===== 測試主程式 =====
    public static void main(String[] args) {
        // 範例 BST
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);

        // 1. BST -> 雙向鏈結串列
        TreeNode head = bstToDoublyList(root);
        System.out.print("排序雙向鏈結串列: ");
        TreeNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.right;
        }
        System.out.println();

        // 2. 排序陣列 -> 平衡 BST
        int[] sorted = {1, 2, 3, 4, 5, 6, 7};
        TreeNode balancedRoot = sortedArrayToBST(sorted);
        System.out.println("平衡 BST 中序: " + inorderTraversal(balancedRoot));

        // 3. 檢查平衡
        System.out.println("是否平衡: " + isBalanced(root));

        // 4. BST -> 大於等於總和樹
        bstToGreaterSumTree(root);
        System.out.println("BST 轉大於等於總和樹中序: " + inorderTraversal(root));
    }

    private static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inOrder(root, res);
        return res;
    }

    private static void inOrder(TreeNode node, List<Integer> res) {
        if (node == null) return;
        inOrder(node.left, res);
        res.add(node.val);
        inOrder(node.right, res);
    }
}

