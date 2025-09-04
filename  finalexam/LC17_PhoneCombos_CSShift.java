import java.util.*;

public class LC17_PhoneCombos_CSShift {
    static String[] map = {
        "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String digits = sc.nextLine().trim(); // 輸入數字字串
        sc.close();

        List<String> res = new ArrayList<>();
        if (!digits.isEmpty()) {
            backtrack(digits, 0, new StringBuilder(), res);
        }

        for (String s : res) {
            System.out.println(s); // 每行一組合
        }
    }

    // 回溯生成組合
    public static void backtrack(String digits, int index, StringBuilder sb, List<String> res) {
        if (index == digits.length()) {
            res.add(sb.toString());
            return;
        }

        String letters = map[digits.charAt(index) - '2'];
        for (char c : letters.toCharArray()) {
            sb.append(c);
            backtrack(digits, index + 1, sb, res);
            sb.deleteCharAt(sb.length() - 1); // 回溯
        }
    }
}
