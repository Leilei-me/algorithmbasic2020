package apractice.class13;

public class Code03_lowestAncestor {
    /**
     * 在头节点的左子树上
     * 在头节点的右子树上
     * 即在左子树，又在右子树上，就是该节点
     * 无
     */

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class Info{
        private boolean findA;
        private boolean findB;
        private Node ans;

        Info(boolean findA, boolean findB, Node ans) {
            this.findA = findA;
            this.findB = findB;
            this.ans = ans;
        }
    }

    public static Node lowestAncestor2(Node x,Node a,Node b) {
        if(a == null || b == null || x == null) {
            return null;
        }

        return process(x, a, b).ans;
    }

    public static Info process(Node x,Node a,Node b) {
        if(a == null || b == null || x == null) {
            return new Info(false,false,null);
        }

        Info leftInfo = process(x.left, a, b);
        Info rightInfo = process(x.right, a, b);
        boolean findA = x == a || leftInfo.findA || leftInfo.findB;
        boolean findB = x == b || rightInfo.findB || rightInfo.findB;

        Node ans = null;
        if(leftInfo.ans != null) {
            ans = leftInfo.ans;
        } else if(rightInfo.ans != null) {
            ans = rightInfo.ans;
        } else if(findA && findB) {
            ans = x;
        }
        return new Info(findA,findB,ans);

    }

}
