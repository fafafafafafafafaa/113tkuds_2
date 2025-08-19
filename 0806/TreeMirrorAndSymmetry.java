public class TreeMirrorAndSymmetry {

    // 二元樹節點
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    // ===== 1. 判斷對稱樹 =====
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    private static boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val)
                && isMirror(t1.left, t2.right)
                && isMirror(t1.right, t2.left);
    }

    // ===== 2. 鏡像樹 =====
    public static TreeNode mirrorTree(TreeNode root) {
        if (root == null) return null;
        TreeNode mirrored = new TreeNode(root.val);
        mirrored.left = mirrorTree(root.right);
        mirrored.right = mirrorTree(root.left);
        return mirrored;
    }

    // ===== 3. 判斷兩棵樹是否互為鏡像 =====
    public static boolean areMirrors(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val)
                && areMirrors(t1.left, t2.right)
                && areMirrors(t1.right, t2.left);
    }

    // ===== 4. 判斷一棵樹是否為另一棵樹的子樹 =====
    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) return false;
        if (isSameTree(root, subRoot)) return true;
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private static boolean isSameTree(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return t1.val == t2.val
                && isSameTree(t1.left, t2.left)
                && isSameTree(t1.right, t2.right);
    }

    // ===== 測試主程式 =====
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);

        System.out.println("是否為對稱樹: " + isSymmetric(root));

        TreeNode mirrored = mirrorTree(root);
        System.out.println("鏡像樹與原樹是否互為鏡像: " + areMirrors(root, mirrored));

        TreeNode subRoot = new TreeNode(2);
        subRoot.left = new TreeNode(3);
        subRoot.right = new TreeNode(4);
        System.out.println("是否為子樹: " + isSubtree(root, subRoot));
    }
}

