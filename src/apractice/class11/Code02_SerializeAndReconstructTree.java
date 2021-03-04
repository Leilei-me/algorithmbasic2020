package apractice.class11;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// 遍历做事
// 序列化值，但没有关系
// 根据特定的反序列化方法，建立关系
// 后序遍历的特别之处，头右左的逆序
public class Code02_SerializeAndReconstructTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Queue<String> preSerial(Node node) {
        Queue ans = new LinkedList();
        preProcess(node,ans);
        return ans;
    }

    private static void preProcess(Node node, Queue ans) {
        if(node == null) {
            ans.add(null);
        } else {
            ans.add(String.valueOf(node.value));
            preProcess(node.left,ans);
            preProcess(node.right,ans);
        }

    }

    public static Queue<String> posSerial(Node node) {
        Queue ans = new LinkedList();
        posProcess(node,ans);
        return ans;
    }

    private static void posProcess(Node node, Queue ans) {
        if(node == null) {
            ans.add(null);
        }else {
            preProcess(node.left,ans);
            preProcess(node.right,ans);
            ans.add(String.valueOf(node.value));
        }

    }

    private static Queue<String> levelSerial(Node node) {
        Queue ans = new LinkedList();
        levelProcess(node,ans);
        return ans;
    }

    private static void levelProcess(Node head, Queue ans) {
        if(head == null) return;
        Queue<Node> helpQueue = new LinkedList<>();
        // 按层遍历节点不为null,入队列
        helpQueue.add(head);
        if(!helpQueue.isEmpty()) {
            Node node = helpQueue.poll();
            ans.add(String.valueOf(node.value));
            if(node.left == null) {
                ans.add(null);
            }else {
                helpQueue.add(node.left);
            }

            if(node.right == null) {
                ans.add(null);
            }else {
                helpQueue.add(node.right);
            }
        }
     }

     public static Node buildByPreQueue(Queue<String> serialQueue) {
        if(serialQueue.size() < 1 || serialQueue == null) {
            return null;
        }
        Node node = preBulid(serialQueue);
        return node;
     }

    private static Node preBulid(Queue<String> serialQueue) {
        if(serialQueue == null || serialQueue.size() < 1) {
            return null;
        }
        // 直接在转换前就报错了
        String str = serialQueue.poll();
        if(str == null) {
            return null;
        }
        Integer value = Integer.valueOf(str);

        Node node = new Node(value);
        node.left = preBulid(serialQueue);
        node.right = preBulid(serialQueue);
        return node;
    }

    public static Node buildByPosQueue(Queue<String> serialQueue) {
        if(serialQueue == null || serialQueue.size() < 1) return null;
        Stack<String> stack = new Stack<>();
        while(! serialQueue.isEmpty()){
            stack.push(serialQueue.poll());
        }
        Node node = posBulid(stack);
        return node;
    }

    private static Node posBulid(Stack<String> stack) {
        String str = stack.pop();
        if(str == null) {
            return null;
        }
        Integer value = Integer.valueOf(str);
        Node node = new Node(value);
        node.right = posBulid(stack);
        node.left = posBulid(stack);
        return node;
    }

    private static Node bulidByleaveQueue(Queue<String> serialQueue) {
        if(serialQueue.isEmpty()) {
            return null;
        }

        Node node = leaveBulid(serialQueue);
        return node;
    }

    private static Node leaveBulid(Queue<String> serialQueue) {
        if(serialQueue == null) return null;
        Queue<Node> queue = new LinkedList<>();
        Node head = generate(serialQueue.poll());
        Node node = head;
        while (! serialQueue.isEmpty()) {
            node.left = generate(serialQueue.poll());
            node.right = generate(serialQueue.poll());
            if(node.left != null) {
                queue.add(node.left);
            }
            if(node.right != null) {
                queue.add(node.right);
            }
            if(! queue.isEmpty()) {
                node = queue.poll();
            }
        }
        return head;
    }

    private static Node generate(String value) {
        if(value == null) return null;
        return new Node(Integer.valueOf(value));
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

    // for test
    public static boolean isSameValueStructure(Node head1, Node head2) {
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1 != null && head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1.value != head2.value) {
            return false;
        }
        return isSameValueStructure(head1.left, head2.left) && isSameValueStructure(head1.right, head2.right);
    }

    // for test
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
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Queue<String> pre = preSerial(head);
            Queue<String> pos = posSerial(head);
            Queue<String> level = levelSerial(head);
            Node preBuild = buildByPreQueue(pre);
            Node posBuild = buildByPosQueue(pos);
            Node levelBuild = leaveBulid(level);
            if (!isSameValueStructure(preBuild, posBuild) || !isSameValueStructure(posBuild, levelBuild)) {
                System.out.println("Oops!");
                printTree(head);
                printTree(preBuild);
                printTree(posBuild);
                printTree(levelBuild);
                break;
            }
        }
        System.out.println("test finish!");

    }

}
