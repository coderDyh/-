from typing import Optional, List

from listnode.TreeNode import TreeNode


def inorderTraversal(self, root: Optional[TreeNode]) -> List[int]:
    res = []
    stack = []
    cur = root
    while cur or stack:
        while cur:
            stack.append(cur)
            cur = cur.left
        cur = stack.pop()
        res.append(cur.data)
        cur = cur.right
    return res