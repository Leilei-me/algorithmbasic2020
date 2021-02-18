package apractice.class03;

public class Code03_DoubleEndsQueneToStackAndQueue {
    public static class DoubleNode<T>{
        public DoubleNode last;
        public DoubleNode next;
        public T value;
        public DoubleNode (T data) {
            value = data;
        }
    }

    public static class Queue<T>{
        DoubleNode tail;
        DoubleNode head;
        int size = 0;

        private int push(T value) {
            if(size > 0){
                DoubleNode node = new DoubleNode(value);
                tail.next = node;
                node.last = tail;
                tail = node;
                size ++;
            }else {
               head =  new DoubleNode(value);
               tail = head;
            }
            return size;
        }

        private T pop(){
            if(size > 0){
                DoubleNode<T> node = head;
                head = head.next;
                if(head != null){
                    head.last = null;
                    node.next = null;
                }
                return node.value;
            } else {
                System.out.println("该队列为空");
                return null;
            }
        }
    }

    public static class Stack<T> {
        DoubleNode tail;
        DoubleNode head;
        int size = 0;

        private int push(T value) {
            if(size > 0){
                DoubleNode node = new DoubleNode(value);
                tail.next = node;
                node.last = tail;
                tail = node;
                size ++;
            }else {
                head =  new DoubleNode(value);
                tail = head;
            }
            return size;
        }

        private T pop(){
            if(size > 0){
                DoubleNode<T> node = tail;
                tail = tail.last;
                if(tail != null){
                    tail.next = null;
                }
                return node.value;
            } else {
                System.out.println("该栈为空");
                return null;
            }
        }
    }

}
