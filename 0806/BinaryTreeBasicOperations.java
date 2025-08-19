import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeBasicOperations {

    // 二元樹節點
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    // ===== 1. 計算總和與平均值 =====
    public static int sum(TreeNode root) {
        if (root == null) return 0;
        return root.val + sum(root.left) + sum(root.right);
    }

    public static double average(TreeNode root) {
        int totalSum = sum(root);
        int totalNodes = countNodes(root);
        return totalNodes == 0 ? 0 : (double) totalSum / totalNodes;
    }

    private static int countNodes(TreeNode root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    // ===== 2. 找最大值和最小值 =====
    public static int findMax(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        return Math.max(root.val, Math.max(findMax(root.left), findMax(root.right)));
    }

    public static int findMin(TreeNode root) {
        if (root == null) return Integer.MAX_VALUE;
        return Math.min(root.val, Math.min(findMin(root.left), findMin(root.right)));
    }

    // ===== 3. 計算樹的寬度 =====
    public static int maxWidth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int maxWidth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            maxWidth = Math.max(maxWidth, size);
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return maxWidth;
    }

    // ===== 4. 判斷是否為完全二元樹 =====
    public static boolean isComplete(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean end = false;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                end = true;
            } else {
                if (end) return false;
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        return true;
    }

    // ===== 測試主程式 =====
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(12);
        root.right.right = new TreeNode(18);

        System.out.println("總和: " + sum(root));
        System.out.println("平均值: " + average(root));
        System.out.println("最大值: " + findMax(root));
        System.out.println("最小值: " + findMin(root));
        System.out.println("樹的最大寬度: " + maxWidth(root));
        System.out.println("是否為完全二元樹: " + isComplete(root));
    }
}
