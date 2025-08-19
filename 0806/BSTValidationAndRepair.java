import java.util.*;

public class BSTValidationAndRepair {

    // 二元樹節點
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    // ===== 1. 驗證是否為 BST =====
    public static boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, null, null);
    }

    private static boolean isValidBSTHelper(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;
        if ((min != null && root.val <= min) || (max != null && root.val >= max)) return false;
        return isValidBSTHelper(root.left, min, root.val) && isValidBSTHelper(root.right, root.val, max);
    }

    // ===== 2. 找出不符合 BST 規則的節點 =====
    public static List<TreeNode> findInvalidNodes(TreeNode root) {
        List<TreeNode> inorderList = new ArrayList<>();
        inorder(root, inorderList);
        List<TreeNode> invalid = new ArrayList<>();
        for (int i = 1; i < inorderList.size(); i++) {
            if (inorderList.get(i-1).val >= inorderList.get(i).val) {
                invalid.add(inorderList.get(i-1));
                invalid.add(inorderList.get(i));
            }
        }
        return invalid;
    }

    private static void inorder(TreeNode root, List<TreeNode> list) {
        if (root == null) return;
        inorder(root.left, list);
        list.add(root);
        inorder(root.right, list);
    }

    // ===== 3. 修復兩個節點錯誤的 BST =====
    public static void recoverBST(TreeNode root) {
        TreeNode[] nodes = new TreeNode[2];
        TreeNode[] prev = new TreeNode[1]; // 用於追蹤前一個節點
        findSwappedNodes(root, prev, nodes);
        if (nodes[0] != null && nodes[1] != null) {
            int temp = nodes[0].val;
            nodes[0].val = nodes[1].val;
            nodes[1].val = temp;
        }
    }

    private static void findSwappedNodes(TreeNode root, TreeNode[] prev, TreeNode[] nodes) {
        if (root == null) return;
        findSwappedNodes(root.left, prev, nodes);
        if (prev[0] != null && prev[0].val > root.val) {
            if (nodes[0] == null) nodes[0] = prev[0];
            nodes[1] = root;
        }
        prev[0] = root;
        findSwappedNodes(root.right, prev, nodes);
    }

    // ===== 4. 計算最少移除節點數量讓樹成為 BST =====
    // 利用 LIS（Longest Increasing Subsequence）概念
    public static int minRemoveToBST(TreeNode root) {
        List<Integer> inorderList = new ArrayList<>();
        inorderValues(root, inorderList);
        return inorderList.size() - lengthOfLIS(inorderList);
    }

    private static void inorderValues(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inorderValues(root.left, list);
        list.add(root.val);
        inorderValues(root.right, list);
    }

    private static int lengthOfLIS(List<Integer> nums) {
        List<Integer> sub = new ArrayList<>();
        for (int num : nums) {
            int idx = Collections.binarySearch(sub, num);
            if (idx < 0) idx = -(idx + 1);
            if (idx < sub.size()) sub.set(idx, num);
            else sub.add(num);
        }
        return sub.size();
    }

    // ===== 測試主程式 =====
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2); // 錯誤節點

        System.out.println("是否為 BST: " + isValidBST(root));

        List<TreeNode> invalidNodes = findInvalidNodes(root);
        System.out.print("不符合 BST 規則的節點: ");
        for (TreeNode n : invalidNodes) System.out.print(n.val + " ");
        System.out.println();

        recoverBST(root);
        System.out.println("修復後是否為 BST: " + isValidBST(root));

        System.out.println("最少移除節點數量讓樹成為 BST: " + minRemoveToBST(root));
    }
}
