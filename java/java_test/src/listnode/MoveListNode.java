package listnode;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/12/9 16:31
 * @description  再第k个位置移动链表
 */
public class MoveListNode {
    public static ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null) return head;
        int len = 1;
        ListNode cur = head;
        while(cur.next != null) {
            cur = cur.next;
            len ++;
        }
        cur = head;
        k %= len;
        for (int i = 0 ; i < len - k - 1; i++){
            cur = cur.next;
        }
        ListNode node1 = cur.next;
        //将原来的链表断开
        cur.next = null;
        cur = node1;
        while (cur.next != null){
            cur = cur.next;
        }
        cur.next = head;
        return node1;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode rotatedHead = rotateRight(head, 2);
        while (rotatedHead!= null) {
            System.out.print(rotatedHead.value + " ");
            rotatedHead = rotatedHead.next;
        }
    }
}
