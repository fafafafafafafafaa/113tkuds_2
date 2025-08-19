import java.util.ArrayList;
import java.util.List;

public class RecursiveTreePreview {

    // ===== 1. 模擬資料夾結構計算總檔案數 =====
    static class FileNode {
        String name;
        boolean isFile;
        List<FileNode> children;

        FileNode(String name, boolean isFile) {
            this.name = name;
            this.isFile = isFile;
            if (!isFile) children = new ArrayList<>();
        }

        void addChild(FileNode child) {
            if (!isFile) children.add(child);
        }
    }

    public static int countFiles(FileNode node) {
        if (node.isFile) return 1;
        int sum = 0;
        for (FileNode child : node.children) {
            sum += countFiles(child);
        }
        return sum;
    }

    // ===== 2. 列印多層選單結構 =====
    static class Menu {
        String name;
        List<Menu> subMenus;

        Menu(String name) {
            this.name = name;
            subMenus = new ArrayList<>();
        }

        void addSubMenu(Menu menu) {
            subMenus.add(menu);
        }
    }

    public static void printMenu(Menu menu, String indent) {
        System.out.println(indent + menu.name);
        for (Menu sub : menu.subMenus) {
            printMenu(sub, indent + "  ");
        }
    }

    // ===== 3. 巢狀陣列展平 =====
    public static List<Integer> flattenNestedArray(Object[] nested) {
        List<Integer> result = new ArrayList<>();
        flattenHelper(nested, result);
        return result;
    }

    private static void flattenHelper(Object[] nested, List<Integer> result) {
        for (Object obj : nested) {
            if (obj instanceof Integer) result.add((Integer) obj);
            else if (obj instanceof Object[]) flattenHelper((Object[]) obj, result);
        }
    }

    // ===== 4. 巢狀清單最大深度 =====
    public static int maxDepth(Object[] nested) {
        int depth = 0;
        for (Object obj : nested) {
            if (obj instanceof Object[]) {
                depth = Math.max(depth, maxDepth((Object[]) obj));
            }
        }
        return depth + 1; // 本層深度加 1
    }

    // ===== 測試主程式 =====
    public static void main(String[] args) {
        // 1. 測試資料夾檔案數
        FileNode root = new FileNode("root", false);
        root.addChild(new FileNode("file1.txt", true));
        FileNode folder1 = new FileNode("folder1", false);
        folder1.addChild(new FileNode("file2.txt", true));
        folder1.addChild(new FileNode("file3.txt", true));
        root.addChild(folder1);
        System.out.println("總檔案數: " + countFiles(root));

        // 2. 測試多層選單
        Menu menu = new Menu("File");
        Menu newMenu = new Menu("New");
        newMenu.addSubMenu(new Menu("Project"));
        newMenu.addSubMenu(new Menu("File"));
        menu.addSubMenu(newMenu);
        menu.addSubMenu(new Menu("Open"));
        System.out.println("\n選單結構:");
        printMenu(menu, "");

        // 3. 測試巢狀陣列展平
        Object[] nestedArray = {1, new Object[]{2, 3}, new Object[]{4, new Object[]{5, 6}}};
        System.out.println("\n展平結果: " + flattenNestedArray(nestedArray));

        // 4. 測試巢狀清單最大深度
        System.out.println("巢狀清單最大深度: " + maxDepth(nestedArray));
    }
}
