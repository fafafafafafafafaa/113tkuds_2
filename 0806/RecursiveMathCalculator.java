import java.util.HashMap;
import java.util.Map;

public class RecursiveMathCalculator {

    // ===== 組合數 C(n, k) =====
    private static final Map<String, Long> nCkMemo = new HashMap<>();
    public static long combination(int n, int k) {
        if (n < 0 || k < 0 || k > n) throw new IllegalArgumentException("n,k 不可為負且需滿足 0 ≤ k ≤ n");
        if (k == 0 || k == n) return 1L;
        // 對稱性：減少遞迴深度
        if (k > n - k) k = n - k;
        String key = n + "," + k;
        if (nCkMemo.containsKey(key)) return nCkMemo.get(key);
        long ans = combination(n - 1, k - 1) + combination(n - 1, k);
        nCkMemo.put(key, ans);
        return ans;
    }

    // ===== 卡塔蘭數 C(n) =====
    private static final Map<Integer, Long> catalanMemo = new HashMap<>();
    public static long catalan(int n) {
        if (n < 0) throw new IllegalArgumentException("n 需為非負整數");
        if (n == 0) return 1L;
        if (catalanMemo.containsKey(n)) return catalanMemo.get(n);
        long sum = 0L;
        for (int i = 0; i < n; i++) {
            sum += catalan(i) * catalan(n - 1 - i);
        }
        catalanMemo.put(n, sum);
        return sum;
    }

    // ===== 漢諾塔移動步數 hanoi(n) =====
    public static long hanoi(int n) {
        if (n < 0) throw new IllegalArgumentException("n 需為非負整數");
        if (n == 0) return 0L;
        return 2L * hanoi(n - 1) + 1L;
    }

    // ===== 回文數判斷（字串遞迴）=====
    public static boolean isPalindromeString(int x) {
        if (x < 0) return false; // 負號不視為回文
        String s = Integer.toString(x);
        return isPalStr(s, 0, s.length() - 1);
        // 也可改為：return isPalNum(x); 使用數字遞迴版本
    }
    private static boolean isPalStr(String s, int l, int r) {
        if (l >= r) return true;
        if (s.charAt(l) != s.charAt(r)) return false;
        return isPalStr(s, l + 1, r - 1);
    }

    // ===== 回文數判斷（純數字遞迴，不轉字串）=====
    public static boolean isPalindromeNumber(int x) {
        if (x < 0) return false;
        int div = 1;
        while (x / div >= 10) div *= 10; // 找到最高位的除數
        return isPalNumRec(x, div);
    }
    private static boolean isPalNumRec(int x, int div) {
        if (x < 10) return true; // 單一位數
        int left = x / div;
        int right = x % 10;
        if (left != right) return false;
        // 去掉最高與最低位，縮小問題
        int trimmed = (x % div) / 10;
        int nextDiv = div / 100;
        if (nextDiv == 0) return true; // 中心收斂完成
        return isPalNumRec(trimmed, nextDiv);
    }

    // ===== 簡單測試 =====
    public static void main(String[] args) {
        // 組合數
        System.out.println("C(5,2) = " + combination(5, 2));
        System.out.println("C(10,3) = " + combination(10, 3));

        // 卡塔蘭數（注意：成長很快，long 容易溢位，僅示範小 n）
        for (int n = 0; n <= 10; n++) {
            System.out.println("Catalan(" + n + ") = " + catalan(n));
        }

        // 漢諾塔步數
        for (int n = 0; n <= 10; n++) {
            System.out.println("hanoi(" + n + ") = " + hanoi(n));
        }

        // 回文數判斷
        int a = 12321, b = 12345, c = 0, d = 1221, e = -121;
        System.out.println(a + " is palindrome (string)? " + isPalindromeString(a));
        System.out.println(b + " is palindrome (string)? " + isPalindromeString(b));
        System.out.println(c + " is palindrome (number)? " + isPalindromeNumber(c));
        System.out.println(d + " is palindrome (number)? " + isPalindromeNumber(d));
        System.out.println(e + " is palindrome (number)? " + isPalindromeNumber(e));
    }
}
