package sort;

import common.ListNode;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/11/30 14:04
 * @description
 */
public class InsertNode {
    public ListNode insertionSortList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode lastNode = head;
        ListNode cur = head.next;
        while (cur != null){
            if(lastNode.val <= cur.val){
                lastNode = lastNode.next;
            }else {
                ListNode pre = dummy;
                while (pre.next.val <= cur.val){
                    pre = pre.next;
                }
                //插入新的节点
                lastNode.next = cur.next;
                cur.next = pre.next;
                pre.next = cur;
            }
            cur = lastNode.next;
        }
        return dummy.next;
    }
}
