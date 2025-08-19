import java.util.*;

public class TreeReconstruction {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    // ===== 1. 前序 + 中序重建 =====
    public static TreeNode buildTreePreIn(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) inMap.put(inorder[i], i);
        return buildPreIn(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    }

    private static TreeNode buildPreIn(int[] pre, int preStart, int preEnd,
                                       int[] in, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if (preStart > preEnd || inStart > inEnd) return null;
        TreeNode root = new TreeNode(pre[preStart]);
        int inRoot = inMap.get(root.val);
        int leftSize = inRoot - inStart;
        root.left = buildPreIn(pre, preStart + 1, preStart + leftSize, in, inStart, inRoot - 1, inMap);
        root.right = buildPreIn(pre, preStart + leftSize + 1, preEnd, in, inRoot + 1, inEnd, inMap);
        return root;
    }

    // ===== 2. 後序 + 中序重建 =====
    public static TreeNode buildTreePostIn(int[] postorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) inMap.put(inorder[i], i);
        return buildPostIn(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    }

    private static TreeNode buildPostIn(int[] post, int postStart, int postEnd,
                                        int[] in, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if (postStart > postEnd || inStart > inEnd) return null;
        TreeNode root = new TreeNode(post[postEnd]);
        int inRoot = inMap.get(root.val);
        int leftSize = inRoot - inStart;
        root.left = buildPostIn(post, postStart, postStart + leftSize - 1, in, inStart, inRoot - 1, inMap);
        root.right = buildPostIn(post, postStart + leftSize, postEnd - 1, in, inRoot + 1, inEnd, inMap);
        return root;
    }

    // ===== 3. 層序重建完全二元樹 =====
    public static TreeNode buildTreeLevelOrder(Integer[] levelOrder) {
        if (levelOrder.length == 0 || levelOrder[0] == null) return null;
        TreeNode root = new TreeNode(levelOrder[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (i < levelOrder.length) {
            TreeNode node = queue.poll();
            if (levelOrder[i] != null) {
                node.left = new TreeNode(levelOrder[i]);
                queue.add(node.left);
            }
            i++;
            if (i < levelOrder.length && levelOrder[i] != null) {
                node.right = new TreeNode(levelOrder[i]);
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }

    // ===== 驗證中序是否正確 =====
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorderHelper(root, res);
        return res;
    }

    private static void inorderHelper(TreeNode node, List<Integer> res) {
        if (node == null) return;
        inorderHelper(node.left, res);
        res.add(node.val);
        inorderHelper(node.right, res);
    }

    // ===== 測試主程式 =====
    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode root1 = buildTreePreIn(preorder, inorder);
        System.out.println("前序+中序重建中序: " + inorderTraversal(root1));

        int[] postorder = {9, 15, 7, 20, 3};
        TreeNode root2 = buildTreePostIn(postorder, inorder);
        System.out.println("後序+中序重建中序: " + inorderTraversal(root2));

        Integer[] levelOrder = {1, 2, 3, 4, 5, 6, 7};
        TreeNode root3 = buildTreeLevelOrder(levelOrder);
        System.out.println("層序重建中序: " + inorderTraversal(root3));
    }
}

