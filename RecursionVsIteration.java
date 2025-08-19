public class RecursionVsIteration {

    // ===== 1. 計算二項式係數 C(n, k) =====
    // 遞迴
    public static int binomialRec(int n, int k) {
        if (k == 0 || k == n) return 1;
        return binomialRec(n - 1, k - 1) + binomialRec(n - 1, k);
    }

    // 迭代 (使用動態規劃)
    public static int binomialIter(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, k); j++) {
                if (j == 0 || j == i) dp[i][j] = 1;
                else dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
        return dp[n][k];
    }

    // ===== 2. 陣列元素乘積 =====
    // 遞迴
    public static int productRec(int[] arr, int n) {
        if (n == 0) return 1;
        return arr[n - 1] * productRec(arr, n - 1);
    }

    // 迭代
    public static int productIter(int[] arr) {
        int product = 1;
        for (int num : arr) product *= num;
        return product;
    }

    // ===== 3. 計算字串中元音字母數量 =====
    // 遞迴
    public static int countVowelsRec(String str, int index) {
        if (index == str.length()) return 0;
        char c = Character.toLowerCase(str.charAt(index));
        int count = (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') ? 1 : 0;
        return count + countVowelsRec(str, index + 1);
    }

    // 迭代
    public static int countVowelsIter(String str) {
        int count = 0;
        for (char c : str.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) != -1) count++;
        }
        return count;
    }

    // ===== 4. 檢查括號是否配對正確 =====
    // 遞迴
    public static boolean checkParenthesesRec(String str, int index, int balance) {
        if (balance < 0) return false;
        if (index == str.length()) return balance == 0;
        char c = str.charAt(index);
        if (c == '(') return checkParenthesesRec(str, index + 1, balance + 1);
        else if (c == ')') return checkParenthesesRec(str, index + 1, balance - 1);
        else return checkParenthesesRec(str, index + 1, balance);
    }

    // 迭代
    public static boolean checkParenthesesIter(String str) {
        int balance = 0;
        for (char c : str.toCharArray()) {
            if (c == '(') balance++;
            else if (c == ')') balance--;
            if (balance < 0) return false;
        }
        return balance == 0;
    }

    // ===== 測試主程式 =====
    public static void main(String[] args) {
        // 測試二項式係數
        System.out.println("C(5, 2) 遞迴: " + binomialRec(5, 2));
        System.out.println("C(5, 2) 迭代: " + binomialIter(5, 2));

        // 測試陣列乘積
        int[] arr = {2, 3, 4};
        System.out.println("陣列乘積 遞迴: " + productRec(arr, arr.length));
        System.out.println("陣列乘積 迭代: " + productIter(arr));

        // 測試元音數量
        String str = "RecursionVsIteration";
        System.out.println("元音數 遞迴: " + countVowelsRec(str, 0));
        System.out.println("元音數 迭代: " + countVowelsIter(str));

        // 測試括號檢查
        String p1 = "(())()";
        String p2 = "(()))(";
        System.out.println("括號檢查 遞迴 (" + p1 + "): " + checkParenthesesRec(p1, 0, 0));
        System.out.println("括號檢查 迭代 (" + p1 + "): " + checkParenthesesIter(p1));
        System.out.println("括號檢查 遞迴 (" + p2 + "): " + checkParenthesesRec(p2, 0, 0));
        System.out.println("括號檢查 迭代 (" + p2 + "): " + checkParenthesesIter(p2));
    }
}
