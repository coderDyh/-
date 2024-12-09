package listnode;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/12/9 10:41
 * @description 扁平化
 */
public class FlattenSolution {
    public DListNode flatten(DListNode head) {
        dfs(head);
        return head;
    }

    private DListNode dfs(DListNode head) {
        if (head == null) {
            return head;
        }
        DListNode cur = head;
        DListNode lastNode = null;
        while (cur != null) {
            DListNode next = cur.next;
            if(cur.child != null){
                DListNode childLast = dfs(cur.child);
                next = cur.next;
                //将node与next相连
                cur.next = cur.child;
                cur.child.prev = cur;
                //如果next不为空，next与last相连
                if (next != null){
                    childLast.next = next;
                    next.prev = childLast;
                }
                //子节点为null
                cur.child = null;
                lastNode = childLast;
            }else {
                lastNode = cur;
            }
            cur = cur.next;
        }
        return lastNode;
    }
}
