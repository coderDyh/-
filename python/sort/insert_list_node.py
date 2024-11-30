from common.ListNode import ListNode


def insertionSortList(head: ListNode)->ListNode:
    if not head or not head.next:
        return head
    dummy = ListNode(0)
    dummy.next = head
    lastNode = head
    cur = head.next
    while cur:
        if lastNode.val <= cur.val:
            lastNode = lastNode.next
        else:
            pre = dummy
            while pre.next.val <= cur.val:
                pre = pre.next
            lastNode.next = cur.next
            cur.next = pre.next
            pre.next = cur
        cur = lastNode.next
    return dummy.next
