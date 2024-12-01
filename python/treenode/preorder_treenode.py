from typing import Optional, List

from listnode import TreeNode


def preorderTraversal(self, root: Optional[TreeNode]) -> List[int]:
    if not root:
        return []
    res = list()
    stack = []
    node = root
    while stack or node:
        while node:
            res.append(node.val)
            stack.append(node)
            node = node.left
        node = stack.pop()
        node = node.right
    return res