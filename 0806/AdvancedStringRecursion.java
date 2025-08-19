import java.util.HashSet;
import java.util.Set;

public class AdvancedStringRecursion {

    // ===== 1. 遞迴產生字串的所有排列組合 =====
    public static void permute(String str) {
        permuteHelper("", str);
    }

    private static void permuteHelper(String prefix, String remaining) {
        if (remaining.isEmpty()) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < remaining.length(); i++) {
                permuteHelper(
                        prefix + remaining.charAt(i),
                        remaining.substring(0, i) + remaining.substring(i + 1)
                );
            }
        }
    }

    // ===== 2. 遞迴實作字串匹配演算法 (子字串是否存在) =====
    public static boolean isMatch(String text, String pattern) {
        return matchHelper(text, pattern, 0, 0);
    }

    private static boolean matchHelper(String text, String pattern, int i, int j) {
        if (j == pattern.length()) return true;
        if (i == text.length()) return false;
        if (text.charAt(i) == pattern.charAt(j)) {
            return matchHelper(text, pattern, i + 1, j + 1);
        } else {
            return matchHelper(text, pattern, i + 1, j);
        }
    }

    // ===== 3. 遞迴移除字串中的重複字符 =====
    public static String removeDuplicates(String str) {
        return removeDupHelper(str, 0, new HashSet<>());
    }

    private static String removeDupHelper(String str, int index, Set<Character> seen) {
        if (index == str.length()) return "";
        char c = str.charAt(index);
        if (seen.contains(c)) {
            return removeDupHelper(str, index + 1, seen);
        } else {
            seen.add(c);
            return c + removeDupHelper(str, index + 1, seen);
        }
    }

    // ===== 4. 遞迴計算字串的所有子字串組合 =====
    public static void generateSubstrings(String str) {
        substrHelper(str, 0, "");
    }

    private static void substrHelper(String str, int index, String current) {
        if (index == str.length()) {
            if (!current.isEmpty()) {
                System.out.println(current);
            }
            return;
        }
        // 包含當前字元
        substrHelper(str, index + 1, current + str.charAt(index));
        // 不包含當前字元
        substrHelper(str, index + 1, current);
    }

    // ===== 測試主程式 =====
    public static void main(String[] args) {
        // 測試排列組合
        System.out.println("排列組合 (abc):");
        permute("abc");

        // 測試字串匹配
        System.out.println("\n字串匹配:");
        System.out.println(isMatch("hello world", "hello")); // true
        System.out.println(isMatch("hello world", "world")); // true
        System.out.println(isMatch("hello world", "abc"));   // false

        // 測試移除重複字符
        System.out.println("\n移除重複:");
        System.out.println(removeDuplicates("banana")); // "ban"

        // 測試所有子字串組合
        System.out.println("\n子字串組合 (abc):");
        generateSubstrings("abc");
    }
}
