package apractice.class11;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Code05_TreeMaxWidth {
    public static class Node{
        private int value;
        private Node left;
        private Node right;

        Node(int value) {
            this.value = value;
        }
    }

    // 当前节点层数与统计层数，搞混，导致下一层的节点层数标记变小。
    public static int maxWidthUseMap(Node root) {
        if (root == null) {
            return 0;
        }
        int max = 0;
        int nodeLevel = 1;
        int levelNum = 0;
        Queue<Node> queue = new LinkedList<>();
        Map<Node, Integer> nodeMap = new HashMap<>();
        nodeMap.put(root, 1);
        queue.add(root);
        while (! queue.isEmpty()) {
            Node cur = queue.poll();
            int curLevel = nodeMap.get(cur);
            if (cur.left != null) {
                nodeMap.put(cur.left, curLevel + 1);
                queue.add(cur.left);
            }

            if (cur.right != null) {
                nodeMap.put(cur.right, curLevel + 1);
                queue.add(cur.right);
            }

            if (curLevel == nodeLevel) {
                levelNum++;
            } else {
                max = Math.max(max, levelNum);
                levelNum = 1;
                nodeLevel++;
            }
        }
        // 边界问题，最后一层的遍历，直接跳出循环，不会进入取最大值的选择分支中。
        max = Math.max(max, levelNum);
        return max;
    }

    // 拉下的
    public static int maxWidthNoMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node curEnd = head; // 当前层，最右节点是谁
        Node nextEnd = null; // 下一层，最右节点是谁
        int max = 0;
        int curLevelNodes = 0; // 当前层的节点数
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.left != null) {
                queue.add(cur.left);
                nextEnd = cur.left;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            curLevelNodes++;
            if (cur == curEnd) {
                max = Math.max(max, curLevelNodes);
                curLevelNodes = 0;
                curEnd = nextEnd;
            }
        }
        return max;
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
            if (maxWidthUseMap(head) != maxWidthNoMap(head)) {
                System.out.println("Oops!");
                System.out.println(maxWidthUseMap(head));
                System.out.println(maxWidthNoMap(head));
                printTree(head);
                break;
            }
        }
        System.out.println("finish!");

    }
  }
