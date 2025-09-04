// 檔名：LC21_MergeTwoLists_Clinics.java
import java.util.*;

public class LC21_MergeTwoLists_Clinics {

    // 定義單向鏈結節點
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 第一院區節點數
        int m = sc.nextInt(); // 第二院區節點數

        ListNode dummy1 = new ListNode(0), curr1 = dummy1;
        for (int i = 0; i < n; i++) {
            curr1.next = new ListNode(sc.nextInt());
            curr1 = curr1.next;
        }

        ListNode dummy2 = new ListNode(0), curr2 = dummy2;
        for (int i = 0; i < m; i++) {
            curr2.next = new ListNode(sc.nextInt());
            curr2 = curr2.next;
        }
        sc.close();

        ListNode merged = mergeTwoLists(dummy1.next, dummy2.next);

        // 輸出合併後序列
        ListNode curr = merged;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) System.out.print(" ");
            curr = curr.next;
        }
    }

    // 合併兩條已排序鏈結串列
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0); // 預防特殊處理
        ListNode tail = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        // 接上剩餘部分
        if (l1 != null) tail.next = l1;
        if (l2 != null) tail.next = l2;

        return dummy.next;
    }
}
