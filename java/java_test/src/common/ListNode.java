package common;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/11/30 14:04
 * @description
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(){}

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
