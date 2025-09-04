// 檔名：LC32_LongestValidParen_Metro.java
import java.util.*;

public class LC32_LongestValidParen_Metro {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().trim(); // 讀取括號字串
        sc.close();

        System.out.println(longestValidParentheses(s));
    }

    // 計算最長合法片段長度
    public static int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // 棧底作基準
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(i); // 遇左括號，存索引
            } else {
                stack.pop(); // 遇右括號，嘗試匹配
                if (stack.isEmpty()) {
                    stack.push(i); // 無匹配 → 設新基準
                } else {
                    // 長度 = 當前索引 - 棧頂索引
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }

        return maxLen;
    }
}
