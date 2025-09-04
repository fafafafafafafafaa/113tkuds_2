// 檔名：LC23_MergeKLists_Hospitals.java
import java.util.*;

public class LC23_MergeKLists_Hospitals {

    // 定義單向鏈結節點
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt(); // 串列數量
        sc.nextLine();

        ListNode[] lists = new ListNode[k];

        // 讀入每條已排序鏈結串列
        for (int i = 0; i < k; i++) {
            ListNode dummy = new ListNode(0);
            ListNode curr = dummy;
            while (true) {
                int val = sc.nextInt();
                if (val == -1) break; // 每行以 -1 結束
                curr.next = new ListNode(val);
                curr = curr.next;
            }
            lists[i] = dummy.next;
        }
        sc.close();

        ListNode merged = mergeKLists(lists);

        // 輸出合併後序列
        ListNode curr = merged;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) System.out.print(" ");
            curr = curr.next;
        }
    }

    // 合併 k 條已排序鏈結串列
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));

        // 初始將非空頭節點加入堆
        for (ListNode node : lists) {
            if (node != null) pq.offer(node);
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (!pq.isEmpty()) {
            ListNode node = pq.poll(); // 取最小值
            tail.next = node;
            tail = tail.next;
            if (node.next != null) pq.offer(node.next); // 推進原串列
        }

        return dummy.next;
    }
}
