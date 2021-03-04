package apractice.class09;

public class Code03_SmallerEqualBigger {

    public static class Node{
        private int value;
        private Node next;

        Node(int value) {
            this.value = value;
        }
    }
    public static Node smallerEqualBigger1(Node head, int pivot) {
        if(head == null) {
            return null;
        }
        int i = 0;
        Node cur = head;
        while(cur != null) {
            i++;
            cur = cur.next;
        }

        Node[] nodes = new Node[i];
        cur = head;
        for(int j=0;j<nodes.length;j++) {
            nodes[j] = cur;
            cur = cur.next;
        }

        arrPartition(nodes,pivot);

        head = nodes[0];
        for(int k=1;k<nodes.length;k++) {
            nodes[k-1].next = nodes[k];
        }
        nodes[nodes.length - 1].next = null;

        return head;
    }

    // 索引指针，要小于大于边界
    private static void arrPartition(Node[] nodes, int pivot) {
        int less = -1;
        int more = nodes.length;
        for(int i=0;i<nodes.length&&i<more;i++) {
            if(nodes[i].value < pivot) {
                swap(nodes, i, ++less);
            } else if(nodes[i].value > pivot) {
                swap(nodes, i, --more);
            }
        }

    }

    private static void swap(Node[] nodes, int j, int i) {
        Node temp = nodes[j];
        nodes[j] = nodes[i];
        nodes[i] = temp;
    }

    // 判断该节点移动后，它现阶段的关系
    public static Node smallerEqualBigger2(Node head, int pivot) {
        if (head == null) {
            return null;
        }

        Node smallHead = null;
        Node smallTail = null;
        Node midHead = null;
        Node midTail = null;
        Node bigHead = null;
        Node bigTail = null;
        Node next = null;

        while(head != null) {
            next = head.next;
            head.next =null;
            if(head.value < pivot) {
                if(smallHead == null) {
                    smallHead = head;
                    smallTail = head;
                }else {
                    smallTail.next = head;
                    smallTail = head;
                }
            }else if(head.value == pivot) {
                if(midHead == null) {
                    midHead = head;
                    midTail = head;
                }  else{
                    midTail.next = head;
                    midTail = head;
                }
            }else {
                if(bigHead == null) {
                    bigHead = head;
                    bigTail = head;
                }  else{
                    bigTail.next = head;
                    bigTail = head;
                }
            }

            head = next;
        }

        if(smallTail != null) {
            smallTail.next = midHead;
            midTail = midTail != null?midTail:smallTail;
        }
        if(midTail != null) {
            midTail.next = bigHead;
        }

//        bigTail.next = null;
        head = smallHead != null ? smallHead:(midHead != null?midHead:bigHead);
        return head;
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
//        head1 = smallerEqualBigger1(head1, 4);
        head1 = smallerEqualBigger2(head1, 5);
//		head1 = smallerEqualBigger2(head1, 5);
        printLinkedList(head1);

    }


}
