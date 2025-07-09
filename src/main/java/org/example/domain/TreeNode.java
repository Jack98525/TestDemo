package org.example.domain;

public class TreeNode {
    int val;
    TreeNode left,right;
    public TreeNode(int val) {
        this.val = val;
    }

    void insert(TreeNode root,int val) {
        if (val < root.val) {
            if (root.left == null) {
                root.left = new TreeNode(val);
            } else {
                insert(root.left,val);
            }
        }else if (val > root.val) {
            if (root.right == null) {
                root.right = new TreeNode(val);
            } else {
                insert(root.right,val);
            }


        }

    }

}
