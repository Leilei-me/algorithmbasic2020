package apractice.class12;

public class Code04_IsFull {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static boolean isFull1(Node head) {
        if (head == null) {
            return true;
        }
        int height = h(head);
        int nodes = n(head);
        return (1 << height) - 1 == nodes;
    }

    public static int h(Node head) {
        if (head == null) {
            return 0;
        }
        return Math.max(h(head.left), h(head.right)) + 1;
    }

    public static int n(Node head) {
        if (head == null) {
            return 0;
        }
        return n(head.left) + n(head.right) + 1;
    }

    public static class Info{
        private boolean isFull;
        private int height;

        Info(boolean isFull, int height) {
            this.isFull = isFull;
            this.height = height;
        }
    }

    public static Info process(Node node) {
        if(node == null) {
            return new Info(true,0);
        }

        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        int height = Math.max(leftInfo.height,rightInfo.height) + 1;
        boolean isFull = true;
        if(leftInfo.height != rightInfo.height) {
            isFull = false;
        }

        if(! leftInfo.isFull || !rightInfo.isFull) {
            isFull = false;
        }

        return new Info(isFull,height);
    }

    public static boolean isFull2(Node node) {
        if(node == null) {
            return true;
        }
        return process(node).isFull;
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

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isFull1(head) != isFull2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
