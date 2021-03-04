package apractice.class12;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// 已节点x为出发点，从左右节点各要获得什么属性，两边需要得属性及节点需要的属性
public class Code02_IsBST {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static boolean isBST1(Node head) {
        if (head == null) {
            return true;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return false;
            }
        }
        return true;
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }
//    public static boolean isBST1(Node root) {
//        if(root == null) {
//            return true;
//        }
//
//        boolean isBST = true;
//        Queue<Node> queue = new LinkedList<>();
//        while(! queue.isEmpty()) {
//            Node node = queue.poll();
//            if(node.left != null) {
//                if(node.left.value >= node.value) {
//                    isBST = false;
//                    break;
//                }
//                queue.add(node.left);
//            }
//
//            if(node.right != null) {
//                if(node.right.value >= node.value) {
//                    isBST = false;
//                    break;
//                }
//                queue.add(node.right);
//            }
//        }
//        return isBST;
//    }

    public static boolean isBST2(Node root) {
        if (root == null) {
            return true;
        }

        return process(root).isBST;
    }

    public static class Info{
        private boolean isBST;
        private int max;
        private int min;

        Info(boolean isBST, int max, int min){
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    /**
     * 左，右子树是搜索二叉树
     * 左子树最大值小于节点
     *
     * @param node
     * @return
     */
    public static Info process(Node node) {
        if(node == null) {
            return null;
        }

        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        //节点最大值 左右子树最大值，与当前节点值取最大值
        // 别多想了，傻瓜式的把情况都遍历下去

        int max = node.value;
        if(leftInfo != null) {
            max = Math.max(max, leftInfo.max);
        }

        if(rightInfo != null) {
            max = Math.max(max, rightInfo.max);
        }

        int min = node.value;

        if(leftInfo != null) {
            min = Math.min(min, leftInfo.min);
        }

        if(rightInfo != null) {
            min = Math.min(min, rightInfo.min);
        }

        boolean isBST = true;

        if(leftInfo != null && leftInfo.isBST == false) {
            isBST = false;
        }

        if(rightInfo != null && rightInfo.isBST == false) {
            isBST = false;
        }

        if (leftInfo != null && leftInfo.max >= node.value) {
            isBST = false;
        }
        if (rightInfo != null && rightInfo.min <= node.value) {
            isBST = false;
        }
        return new Info(isBST, max, min);

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
        int maxLevel = 3;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBST1(head) != isBST2(head)) {

                System.out.println("Oops!");
                printTree(head);
                System.out.println(isBST1(head));
                System.out.println(isBST2(head));
                break;
            }
        }
        System.out.println("finish!");
    }
}
