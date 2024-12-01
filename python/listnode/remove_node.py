#移除列表指定节点

from typing import Optional

from common.ListNode import ListNode


def removeElements(self, head: Optional[ListNode], val: int) -> Optional[ListNode]:
    if not head:
        return head
    dummy = ListNode(0)
    dummy.next = head
    pre = dummy
    while pre.next is not None:
        if pre.next.val == val:
            pre.next = pre.next.next
        else:
            pre = pre.next
    return dummy.next