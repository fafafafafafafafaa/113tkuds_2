import java.util.*;

class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1); map.put('V', 5); map.put('X', 10);
        map.put('L', 50); map.put('C', 100); map.put('D', 500);
        map.put('M', 1000);
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            int v = map.get(s.charAt(i));
            if (i + 1 < s.length() && v < map.get(s.charAt(i + 1))) {
                ans -= v;  // 出現 IV, IX, XL, XC, CD, CM 等減法情況
            } else {
                ans += v;
            }
        }
        return ans;
    }
}