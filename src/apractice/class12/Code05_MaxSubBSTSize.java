package apractice.class12;

import java.util.ArrayList;

// 除1外，对数器，树生成函数等还未写
// 若该节点的树尺寸，与其最大子树尺寸一致，其为搜索树
// 因无相关的参数返回，故返回null，特别还要比较
public class Code05_MaxSubBSTSize {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class Info{
        public int maxBSTSubtreeSize;
        public int allSize;
        private int max;
        private int min;

        Info(int maxBSTSubtreeSize, int allSize, int max, int min) {
            this.maxBSTSubtreeSize = maxBSTSubtreeSize;
            this.allSize = allSize;
            this.max = max;
            this.min = min;
        }
    }


    public static int maxSubBSTSize2(Node node) {
            if(node == null) {
                return 0;
            }

            return process(node).maxBSTSubtreeSize;

        }

        public static Info process(Node node) {
            if(node == null) {
                return null;
            }
            Info leftInfo = process(node.left);
            Info rightInfo = process(node.right);

            int max = node.value;
            if(leftInfo != null) {
                max = Math.max(max, leftInfo.max);
            }
            if(rightInfo != null) {
                max = Math.max(max,rightInfo.max);
            }

            int min = node.value;
            if(leftInfo != null) {
                min = Math.min(min, leftInfo.min);
            }
            if(rightInfo != null) {
                min = Math.min(min, rightInfo.min);
            }

            int allSize = 1;
            int p1 = 1;
            int p2 = 2;
            if(leftInfo != null) {
                allSize = allSize + leftInfo.allSize;
                if(leftInfo.allSize == leftInfo.maxBSTSubtreeSize && leftInfo.max < node.value)  {
                    p1 = p1 + leftInfo.maxBSTSubtreeSize;
                } else {
                    p2 = Math.max(p2, leftInfo.maxBSTSubtreeSize);
                }
            }
            if(rightInfo != null) {
                allSize = allSize + rightInfo.allSize;
                if(rightInfo.allSize == rightInfo.maxBSTSubtreeSize && rightInfo.min > node.value) {
                    p1 = p1 + rightInfo.maxBSTSubtreeSize;
                } else {
                    p2 = Math.max(p2, rightInfo.maxBSTSubtreeSize);
                }
            }

            return new Info(Math.max(p1,p2),allSize,max,min);

        }

    public static int getBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return 0;
            }
        }
        return arr.size();
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    public static int maxSubBSTSize1(Node head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(maxSubBSTSize1(head.left), maxSubBSTSize1(head.right));
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
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxSubBSTSize1(head) != maxSubBSTSize2(head)) {
                System.out.println("Oops!");
                System.out.println(maxSubBSTSize1(head));
                System.out.println(maxSubBSTSize2(head));
                printTree(head);
                break;
            }
        }
        System.out.println("finish!");
    }
}
