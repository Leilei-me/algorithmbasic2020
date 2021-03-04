package apractice.class10;

import java.util.Stack;

// 把null 也看成一个节点，一个空节点
public class Code03_UnRecursiveTraversalBT1 {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    public static void pre(Node head) {
        Stack<Node> tempStack = new Stack<>();
        System.out.println(head.value);
        tempStack.push(head.right);
        tempStack.push(head.left);
        while(! tempStack.isEmpty()) {
            Node popNode = tempStack.pop();
            if(popNode != null) {
                System.out.println(popNode.value);
                tempStack.push(popNode.right);
                tempStack.push(popNode.left);
            }
        }
    }

    public static void in(Node head) {
        if(head != null) {
            Stack<Node> tempStack = new Stack<>();
            while(! tempStack.isEmpty() || head != null) {
                if(head != null) {
                    tempStack.push(head);
                    head = head.left;
                } else {
                    head = tempStack.pop();
                    System.out.print(head.value);
                    head = head.right;
                }
            }
        }
        System.out.println();

    }

    // 头右左
    public static void pos(Node head) {
        Stack<Node> tempStack = new Stack<>();
        Stack<Node> dataStack = new Stack<>();
        dataStack.push(head);
        tempStack.push(head.left);
        tempStack.push(head.right);
        while(! tempStack.isEmpty()) {
            Node popNode = tempStack.pop();
            if(popNode != null) {
                dataStack.push(popNode);
                tempStack.push(popNode.left);
                tempStack.push(popNode.right);

            }
        }

        while (! dataStack.isEmpty()){
            System.out.println(dataStack.pop().value);
        }

    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        pre(head);
        System.out.println("========");
        in(head);
        System.out.println("========");
        pos(head);
        System.out.println("========");
//        pos2(head);
        System.out.println("========");
    }
}
