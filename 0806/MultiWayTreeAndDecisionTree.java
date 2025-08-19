import java.util.*;

public class MultiWayTreeAndDecisionTree {

    // ===== 多路樹節點 =====
    static class TreeNode {
        String val;
        List<TreeNode> children;
        TreeNode(String val) {
            this.val = val;
            children = new ArrayList<>();
        }
    }

    // ===== 1. 深度優先走訪 (DFS) =====
    public static void dfs(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        for (TreeNode child : root.children) {
            dfs(child);
        }
    }

    // ===== 2. 廣度優先走訪 (BFS) =====
    public static void bfs(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val + " ");
            queue.addAll(node.children);
        }
    }

    // ===== 3. 計算高度 =====
    public static int getHeight(TreeNode root) {
        if (root == null) return 0;
        int maxChildHeight = 0;
        for (TreeNode child : root.children) {
            maxChildHeight = Math.max(maxChildHeight, getHeight(child));
        }
        return maxChildHeight + 1;
    }

    // ===== 4. 計算每個節點的度數 =====
    public static void printDegrees(TreeNode root) {
        if (root == null) return;
        System.out.println("節點 " + root.val + " 的度數: " + root.children.size());
        for (TreeNode child : root.children) {
            printDegrees(child);
        }
    }

    // ===== 5. 簡單決策樹範例 (猜數字遊戲) =====
    public static void simpleDecisionTree() {
        TreeNode root = new TreeNode("Is it > 50?");
        TreeNode yes = new TreeNode("Is it > 75?");
        TreeNode no = new TreeNode("Is it > 25?");

        root.children.add(yes);
        root.children.add(no);

        yes.children.add(new TreeNode("Guess 76-100"));
        yes.children.add(new TreeNode("Guess 51-75"));

        no.children.add(new TreeNode("Guess 26-50"));
        no.children.add(new TreeNode("Guess 0-25"));

        System.out.println("決策樹 DFS 走訪: ");
        dfs(root);
        System.out.println("\n決策樹 BFS 走訪: ");
        bfs(root);
    }

    // ===== 測試主程式 =====
    public static void main(String[] args) {
        TreeNode root = new TreeNode("A");
        TreeNode b = new TreeNode("B");
        TreeNode c = new TreeNode("C");
        TreeNode d = new TreeNode("D");
        TreeNode e = new TreeNode("E");
        TreeNode f = new TreeNode("F");

        root.children.add(b);
        root.children.add(c);
        b.children.add(d);
        b.children.add(e);
        c.children.add(f);

        System.out.print("DFS: ");
        dfs(root);
        System.out.println();

        System.out.print("BFS: ");
        bfs(root);
        System.out.println();

        System.out.println("樹的高度: " + getHeight(root));
        printDegrees(root);

        System.out.println("\n=== 簡單決策樹示範 ===");
        simpleDecisionTree();
    }
}
