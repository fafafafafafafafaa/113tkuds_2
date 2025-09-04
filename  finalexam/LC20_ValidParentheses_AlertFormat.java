// 檔名：LC20_ValidParentheses_AlertFormat.java
import java.util.*;

public class LC20_ValidParentheses_AlertFormat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().trim(); // 讀取括號字串
        sc.close();

        System.out.println(isValid(s));
    }

    // 檢查括號是否合法
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        // 閉括號 -> 對應開括號
        Map<Character, Character> map = Map.of(
            ')', '(',
            ']', '[',
            '}', '{'
        );

        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                // 遇閉括號，檢查棧頂是否匹配
                if (stack.isEmpty() || stack.pop() != map.get(c)) {
                    return false; // 不匹配直接返回 false
                }
            } else {
                // 遇開括號，放入棧
                stack.push(c);
            }
        }

        // 全部匹配且棧空才合法
        return stack.isEmpty();
    }
}
