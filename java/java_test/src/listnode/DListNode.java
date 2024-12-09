package listnode;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/12/9 10:41
 * @description 双向列表
 */
public class DListNode {
    int val;
    DListNode prev,next,child;
    public DListNode(int val) {
        this.val = val;
    }
}
