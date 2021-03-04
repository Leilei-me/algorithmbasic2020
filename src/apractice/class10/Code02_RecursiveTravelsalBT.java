package apractice.class10;

import yuanshi.class10.Code02_RecursiveTraversalBT;

public class Code02_RecursiveTravelsalBT {
    public static class TreeNode{
        private int value;
        private TreeNode left;
        private TreeNode right;

        TreeNode(int value) {
            this.value = value;
        }
    }

    public static void pre(TreeNode head) {
        if(head == null) {
            return;
        }

        System.out.println(head.value);
        pre(head.left);
        pre(head.right);
    }

    public static void in(TreeNode head) {
        if(head == null) {
            return;
        }

        in(head.left);
        System.out.println(head.value);
        in(head.right);
    }

    public static void pos(TreeNode head) {
        if(head == null) {
            return;
        }

        pos(head.left);
        pos(head.right);
        System.out.println(head.value);
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);
        head.right.left = new TreeNode(6);
        head.right.right = new TreeNode(7);

        pre(head);
        System.out.println("========");
        in(head);
        System.out.println("========");
        pos(head);
        System.out.println("========");

    }

}
