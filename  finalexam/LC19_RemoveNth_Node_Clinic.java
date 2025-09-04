// 檔名：LC19_RemoveNth_Node_Clinic.java
import java.util.*;

public class LC19_RemoveNth_Node_Clinic {

    // 定義單向鏈結節點
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 節點數量
        ListNode dummy = new ListNode(0); // dummy 預防刪頭
        ListNode curr = dummy;

        for (int i = 0; i < n; i++) {
            curr.next = new ListNode(sc.nextInt());
            curr = curr.next;
        }

        int k = sc.nextInt(); // 倒數第 k 筆
        sc.close();

        ListNode head = removeNthFromEnd(dummy.next, k);

        // 輸出刪除後序列
        curr = head;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) System.out.print(" ");
            curr = curr.next;
        }
    }

    // 刪除倒數第 k 節點
    public static ListNode removeNthFromEnd(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;

        // fast 先走 k 步
        for (int i = 0; i < k; i++) fast = fast.next;

        // fast, slow 同步移動，直到 fast 到尾
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 刪除 slow.next 節點
        slow.next = slow.next.next;

        return dummy.next;
    }
}
