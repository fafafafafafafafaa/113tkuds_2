import java.util.*;

public class LevelOrderTraversalVariations {

    // 二元樹節點
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    // ===== 1. 每層節點存入不同 List =====
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            result.add(level);
        }
        return result;
    }

    // ===== 2. 之字形層序走訪 =====
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean leftToRight = true;
        while (!queue.isEmpty()) {
            LinkedList<Integer> level = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (leftToRight) level.addLast(node.val);
                else level.addFirst(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            leftToRight = !leftToRight;
            result.add(level);
        }
        return result;
    }

    // ===== 3. 每層最後一個節點 =====
    public static List<Integer> lastNodeEachLevel(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeNode last = null;
            for (int i = 0; i < size; i++) {
                last = queue.poll();
                if (last.left != null) queue.add(last.left);
                if (last.right != null) queue.add(last.right);
            }
            result.add(last.val);
        }
        return result;
    }

    // ===== 4. 垂直層序走訪 =====
    public static List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Map<Integer, List<Integer>> map = new TreeMap<>();
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> colQueue = new LinkedList<>();
        nodeQueue.add(root);
        colQueue.add(0);

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int col = colQueue.poll();
            map.computeIfAbsent(col, k -> new ArrayList<>()).add(node.val);
            if (node.left != null) {
                nodeQueue.add(node.left);
                colQueue.add(col - 1);
            }
            if (node.right != null) {
                nodeQueue.add(node.right);
                colQueue.add(col + 1);
            }
        }

        result.addAll(map.values());
        return result;
    }

    // ===== 測試主程式 =====
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        System.out.println("每層節點: " + levelOrder(root));
        System.out.println("之字形層序: " + zigzagLevelOrder(root));
        System.out.println("每層最後節點: " + lastNodeEachLevel(root));
        System.out.println("垂直層序: " + verticalOrder(root));
    }
}

