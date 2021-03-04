package apractice.class12;

import java.util.LinkedList;
import java.util.Queue;

// 不止一次了，逻辑没有错，变量写错了
public class Code01_IsCBT {
    public static class Node{
        private int value;
        private Node left;
        private Node right;

        Node(int value) {
            this.value = value;
        }
    }

// 按层遍历，出现第一个为空的子节点，后面叶子都为空。即已入队列的所有节点的子节点都为null
    public static boolean isCBT1(Node root) {
        if(root == null) {
            return true;
        }
        boolean isCBT = true;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(! queue.isEmpty()) {
            Node node = queue.poll();
            if(node.left == null && node.right != null) {
                isCBT = false;
                break;
            }
            if(node.left != null) {
                queue.add(node.left);
            }
            if(node.right != null) {
                queue.add(node.right);
            }
            if(node.left == null || node.right == null) { //这样写，少过滤了一种情况。就是当前节点的左节点为空，右节点不为空，已经是不完全二叉树。
                while(! queue.isEmpty()) {
                    Node node1 = queue.poll();
                    if(node1.left != null || node1.right != null) {
                        isCBT = false;
                    }
                }
                break;
            }
        }
        return isCBT;

    }

    /**满足完全二叉树条件
     * 1左右子树高度不超过1
     * 2.1 左子树是满二叉树，右子树是满二叉树
     * 2.2 左子树是满二叉树，右子树是完全二叉树
     * 2.3 左子树是完全二叉树，右子树是满二叉树
     *
     * 1 && 2
      */

    public static boolean isCBT2(Node root) {
        if(root == null) {
            return true;
        }
        return process(root).isCBT;
    }

    public static class Info{
        private boolean isFull;
        private boolean isCBT;
        private int height;

        Info(boolean isFull,boolean isCBT,int height) {
            this.isFull = isFull;
            this.isCBT = isCBT;
            this.height = height;
        }
    }

    public static Info process(Node node) {
        if(node == null) {
            return new Info(true,true,0);
        }

        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int height = Math.max(leftInfo.height,rightInfo.height) + 1;
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        boolean isCBT = false;
        if(isFull) {
            isCBT = true;
        } else {
//            if (leftInfo.isCBT && rightInfo.isCBT) {
                if(leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
                    isCBT = true;
                }
                if(leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height) {
                    isCBT = true;
                }
                if(leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
                    isCBT = true;
                }
//            }

        }

        return new Info(isFull,isCBT,height);
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isCBT1(head) != isCBT2(head)) {
                System.out.println("Oops!");
                System.out.println(isCBT1(head));
                System.out.println(isCBT2(head));
                printTree(head);
                break;
            }
        }
        System.out.println("finish!");
    }

}
