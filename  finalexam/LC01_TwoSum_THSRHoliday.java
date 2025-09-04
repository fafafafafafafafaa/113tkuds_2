// 檔名：LC01_TwoSum_THSRHoliday.java
import java.util.*;

public class LC01_TwoSum_THSRHoliday {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 讀取 n 與 target
        int n = sc.nextInt();
        int target = sc.nextInt();

        int[] seats = new int[n];
        for (int i = 0; i < n; i++) {
            seats[i] = sc.nextInt();
        }

        // 使用 HashMap<需要的數, 索引>
        Map<Integer, Integer> map = new HashMap<>();
        int idx1 = -1, idx2 = -1;

        for (int i = 0; i < n; i++) {
            int x = seats[i];
            // 檢查是否有人在等這個 x
            if (map.containsKey(x)) {
                idx1 = map.get(x);
                idx2 = i;
                break; // 找到一組就可以結束
            }
            // 記錄還需要的 target-x
            map.put(target - x, i);
        }

        if (idx1 != -1 && idx2 != -1) {
            System.out.println(idx1 + " " + idx2);
        } else {
            System.out.println("-1 -1");
        }

        sc.close();
    }
}
