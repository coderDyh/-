package treenode;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/12/1 19:26
 * @description 中序
 */
public class InOrder {
    public List<Integer> inorderTraversal(TreeNode root){
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    public void inorder(TreeNode root, List<Integer> res) {
        if(root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(cur != null || !stack.isEmpty()){
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            if(!stack.isEmpty()){
               cur = stack.pop();
               res.add(cur.val);
               cur = cur.right;
            }
        }
    }
}
