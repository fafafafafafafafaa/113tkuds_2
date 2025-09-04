// 檔名：LC03_NoRepeat_TaipeiMetroTap.java
import java.util.*;

public class LC03_NoRepeat_TaipeiMetroTap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().trim(); // 讀取輸入字串
        sc.close();

        // Map<字元, 上次出現的位置>
        Map<Character, Integer> lastIndex = new HashMap<>();

        int maxLen = 0; // 最長長度
        int left = 0;   // 視窗左邊界

        // 右指針遍歷字串
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // 若字元重複，更新左邊界
            if (lastIndex.containsKey(c)) {
                left = Math.max(left, lastIndex.get(c) + 1);
            }

            // 更新當前字元的最後出現位置
            lastIndex.put(c, right);

            // 計算目前視窗長度
            maxLen = Math.max(maxLen, right - left + 1);
        }

        // 輸出最長長度
        System.out.println(maxLen);
    }
}
