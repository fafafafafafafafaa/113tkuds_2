// 檔名：LC24_SwapPairs_Shifts.java
import java.util.*;

public class LC24_SwapPairs_Shifts {

    // 定義單向鏈結節點
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        // 讀入班表節點值
        while (sc.hasNextInt()) {
            curr.next = new ListNode(sc.nextInt());
            curr = curr.next;
        }
        sc.close();

        ListNode swapped = swapPairs(dummy.next);

        // 輸出交換後序列
        curr = swapped;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) System.out.print(" ");
            curr = curr.next;
        }
    }

    // 每兩個節點交換一次
    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode a = prev.next;
            ListNode b = a.next;

            // 交換 a 與 b
            prev.next = b;
            a.next = b.next;
            b.next = a;

            // 移動 prev 到下一對前
            prev = a;
        }

        return dummy.next;
    }
}
