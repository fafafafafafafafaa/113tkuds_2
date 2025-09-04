// 檔名：LC25_ReverseKGroup_Shifts.java
import java.util.*;

public class LC25_ReverseKGroup_Shifts {

    // 定義單向鏈結節點
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt(); // 讀入每組長度 k
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        // 讀入班表節點值
        while (sc.hasNextInt()) {
            curr.next = new ListNode(sc.nextInt());
            curr = curr.next;
        }
        sc.close();

        ListNode reversed = reverseKGroup(dummy.next, k);

        // 輸出反轉後序列
        curr = reversed;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) System.out.print(" ");
            curr = curr.next;
        }
    }

    // 主函式：每 k 個節點反轉
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroupEnd = dummy;

        while (true) {
            // 檢查剩餘長度是否 >= k
            ListNode kth = prevGroupEnd;
            for (int i = 0; i < k; i++) {
                kth = kth.next;
                if (kth == null) return dummy.next; // 不足 k → 保留尾端
            }

            ListNode groupStart = prevGroupEnd.next;
            ListNode nextGroupStart = kth.next;

            // 反轉該組
            ListNode prev = nextGroupStart;
            ListNode curr = groupStart;
            while (curr != nextGroupStart) {
                ListNode tmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = tmp;
            }

            // 接回前一組
            prevGroupEnd.next = kth;
            prevGroupEnd = groupStart;
        }
    }
}
