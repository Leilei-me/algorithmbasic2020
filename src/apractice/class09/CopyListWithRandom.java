package apractice.class09;

import yuanshi.class09.Code04_CopyListWithRandom;

import java.util.HashMap;
import java.util.Map;

// 不知道要几个辅助节点，先new几个用着
// 注意引用前面要变的引用，值得变化。从而调整节点赋值顺序或者或者创建新的变量，临时保存。
// 结尾节点值得变化
public class CopyListWithRandom {
    public static class RandNode{
        private int value;
        private RandNode rand;
        private RandNode next;

        RandNode(int value) {
            this.value = value;
        }
    }

    public static RandNode copyListWithRandom1(RandNode head) {
        if(head == null) {
            return null;
        }

        Map<RandNode,RandNode> nodeMap = new HashMap<RandNode,RandNode>();

        RandNode cur = head;
        while(cur != null) {
            nodeMap.put(cur,new RandNode(cur.value));
            cur = cur.next;
        }
        cur = head;
        RandNode copyHead = nodeMap.get(cur);
        while(cur != null) {
            RandNode copyNode = nodeMap.get(cur);
            copyNode.next = nodeMap.get(cur.next);
            copyNode.rand = nodeMap.get(cur.rand);
            cur = cur.next;
        }
        return copyHead;
    }

    public static RandNode copyListWithRandom2(RandNode head) {

        if(head == null) {
            return null;
        }
        // 复制每一个节点
        RandNode cur = head;
        RandNode next = null;
        while(cur != null) {
            next = cur.next;
            RandNode randNode = new RandNode(cur.value);
            cur.next = randNode;
            randNode.next = next;
            cur = next;

        }

        // 复制每一个random
        cur = head;
        while(cur != null) {
            next = cur.next;
            if(cur.rand != null) {
                next.rand = cur.rand.next;
            }
            cur = next.next;
        }

        // 断开
        cur = head;
        RandNode copyHead = head.next;
        while (cur != null) {
            next = cur.next;
            cur.next = next.next;
            cur = next.next;
            if(cur != null) {
                next.next = next.next.next;
            }

        }
        return copyHead;
    }

    public static void printRandLinkedList(RandNode head) {
        RandNode cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        System.out.print("rand:  ");
        while (cur != null) {
            System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        RandNode head = null;
        RandNode res1 = null;
        RandNode res2 = null;
        printRandLinkedList(head);
        res1 = copyListWithRandom1(head);
        printRandLinkedList(res1);
        res2 = copyListWithRandom2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

        head = new RandNode(1);
        head.next = new RandNode(2);
        head.next.next = new RandNode(3);
        head.next.next.next = new RandNode(4);
        head.next.next.next.next = new RandNode(5);
        head.next.next.next.next.next = new RandNode(6);

        head.rand = head.next.next.next.next.next; // 1 -> 6
        head.next.rand = head.next.next.next.next.next; // 2 -> 6
        head.next.next.rand = head.next.next.next.next; // 3 -> 5
        head.next.next.next.rand = head.next.next; // 4 -> 3
        head.next.next.next.next.rand = null; // 5 -> null
        head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

        System.out.println("原始链表：");
        printRandLinkedList(head);
        System.out.println("=========================");
        res1 = copyListWithRandom1(head);
        System.out.println("方法一的拷贝链表：");
        printRandLinkedList(res1);
        System.out.println("=========================");
        res2 = copyListWithRandom2(head);
        System.out.println("方法二的拷贝链表：");
        printRandLinkedList(res2);
        System.out.println("=========================");
        System.out.println("经历方法二拷贝之后的原始链表：");
        printRandLinkedList(head);
        System.out.println("=========================");

    }
}
