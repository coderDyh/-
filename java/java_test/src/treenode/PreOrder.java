package treenode;

import common.TreeNode;

import java.util.*;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/12/1 17:33
 * @description 前序遍历
 */
public class PreOrder {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
//        preorder(root, res);
        preOrderByStep(root, res);
        return res;
    }

    /**
     * 迭代
     * */
    public void preOrderByStep(TreeNode root, List<Integer> res){
        if(root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            res.add(node.val);
            if (node.right != null){
                stack.push(node.right);
            }
            if(node.left != null){
                stack.push(node.left);
            }
        }
    }
    /**
     * 递归
     * */
    public void preorder(TreeNode root, List<Integer> res) {
        if(root == null) return;
        res.add(root.val);
        preorder(root.left, res);
        preorder(root.right, res);
    }

}
