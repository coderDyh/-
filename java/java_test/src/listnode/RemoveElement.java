package listnode;

import common.ListNode;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/12/1 15:58
 * @description 移除链表元素
 */
public class RemoveElement {
    public static ListNode removeElements(ListNode head, int val) {
        if(head == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        while(pre.next != null){
            if(pre.next.val == val){
                pre.next = pre.next.next;
            }else {
                pre = pre.next;
            }
        }
        return dummy.next;
    }
}
