// 檔名：LC28_StrStr_NoticeSearch.java
import java.util.*;

public class LC28_StrStr_NoticeSearch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String haystack = sc.nextLine(); // 公告全文
        String needle = sc.nextLine();   // 欲搜尋片段
        sc.close();

        int index = strStr(haystack, needle);
        System.out.println(index);
    }

    // 返回 needle 首次出現的索引，找不到回 -1
    public static int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();
        if (m == 0) return 0; // 空字串特例

        for (int i = 0; i <= n - m; i++) { // 每個可能起點
            int j = 0;
            while (j < m && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }
            if (j == m) return i; // 找到完整匹配
        }
        return -1; // 找不到
    }
}
