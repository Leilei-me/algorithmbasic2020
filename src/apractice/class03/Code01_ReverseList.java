package apractice.class03;

import java.util.ArrayList;
import java.util.List;

//Integer.equls
//双向链表验证，双向验证
public class Code01_ReverseList {
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

    public static Node reverseNode(Node head){
        Node pre = null;
        Node next = null;
        while(head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static DoubleNode reserseDoubleNode(DoubleNode head){
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static Node testNode(Node head){
        List<Node> list = new ArrayList<>();
        while(head != null) {
            list.add(head);
            head = head.next;
        }
        if(list.size() == 1) {
            head = list.get(list.size() -1);
            return head;
        };
        for(int i= 1;i < list.size();i--){
            list.get(i).next = list.get(i-1);
        }
        return list.get(list.size() - 1);
    }

    public static void main(String[] args) {
        int testTimes = 50000;
        int maxSize = 10;
        int maxNum = 100;
        int size = (int)(Math.random() * 100) + 1;

        for(int i=0; i<testTimes; i++){
            Node node = generateRandomNode(size,maxNum);
            List<Integer> ans = getOriginalNodeValue(node);
            Node node1 = reverseNode(node);
            if(! checkNodeList(node1,ans)){
                printNode(node);
                printNode(node1);
                System.out.println("node false");
                break;
            }
        }
        System.out.println("succeed");

        DoubleNode doubleNode = generateRandomDoubleNode(size,maxNum);
    }

    private static boolean checkNodeList(Node head, List<Integer> ans) {
        for(int i = ans.size()-1;i>=0;i--){
            if(head.value != ans.get(i)) return false;
            head = head.next;
        }
        return true;
    }

    private static List getOriginalNodeValue(Node head) {
        List<Integer> ans = new ArrayList();
        while(head != null) {
            ans.add(head.value);
            head = head.next;
        }
        return ans;
    }

    private static void printNode(Node reverseNode) {
        while(reverseNode != null) {
            System.out.print(reverseNode.value + " ");
            reverseNode = reverseNode.next;
        }
        System.out.println();
    }

    //想复杂了 直接比较linkList各节点值就行。生成的node,反转后已经不是头节点了。已有方法实现列表反转，进一步验证比较数组有效。
    private static boolean isEqual(Node reverseNode, Node testNode) {
        while(reverseNode != null) {
            if(reverseNode.value != testNode.value) return false;
            reverseNode = reverseNode.next;
            testNode = testNode.next;
        }
        return true;
    }

    private static DoubleNode generateRandomDoubleNode(int size, int maxNum) {
        DoubleNode head = new DoubleNode((int)(Math.random()*maxNum) + 1);
        DoubleNode cur = head;
        for(int i=0;i<size -1;i++){
            DoubleNode doubleNode = new DoubleNode((int)(Math.random()*(maxNum + 1)));
            cur.next = doubleNode;
            doubleNode.last = cur;
            cur = doubleNode;
        }
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

}
