package apractice.class03;

import java.util.List;

public class Code02_DeleteGivenValue {
    public static class Node{
        public Node next;
        public int value;
        public Node (int data) {
            value = data;
        }
    }

    public static class DoubleNode{
        public DoubleNode last;
        public DoubleNode next;
        public int value;
        public DoubleNode (int data) {
            value = data;
        }
    }

    public static Node deleteGivenValue(Node head,int num) {
        if(head == null){
            return head;
        }
        while(head.value == num){
            head = head.next;
            if(head == null){
                return head;
            }
        }
        Node pre = head;
        Node next = head.next;
        while(next != null){
            if(next.value == num) {
                next = next.next;
            }else {
                pre.next = next;
                pre = next;
                next = next.next;
            }
        }
        pre.next = null;
        return head;
    }

    public static DoubleNode deleteGivenDoubleValue(DoubleNode head,int num) {
        if(head == null){
            return head;
        }
        while(head.value == num){
            head = head.next;
            if(head == null){
                return head;
            }
        }
        head.last = null;
        DoubleNode pre = head;
        DoubleNode next = head.next;
        while(next != null){
            if(next.value == num) {
                next = next.next;
            }else {
                pre.next = next;
                next.last = pre;
                pre = next;
                next = next.next;
            }
        }
        pre.next = null;
        return head;
    }

    private static Node generateRandomNode(int size, int maxNum) {
        Node head = new Node((int)(Math.random()*maxNum) + 1);
       Node cur = head;
        for(int i=0;i<size -1;i++){
            Node node = new Node((int)(Math.random()*(maxNum + 1)) );
            cur.next = node;
            cur = node;
        }
        return head;
    }
    private static void printNode(Node reverseNode) {
        while(reverseNode != null) {
            System.out.print(reverseNode.value + " ");
            reverseNode = reverseNode.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int testTimes = 5;
        int maxSize = 10;
        int maxNum = 5;
        int size = (int)(Math.random() * 100) + 1;

        for(int i=0; i<testTimes; i++){
            Node node = generateRandomNode(size,maxNum);
            printNode(node);
            deleteGivenValue(node,4);
            printNode(node);
        }
        System.out.println("succeed");
    }
}
