package apractice.class03;

import java.util.Queue;

// 方法会复制值然后传递，对于引用换位，只是换了复制的引用。所以不要用方法，直接进行换位。
public class Code07_TwoQueueImplementStack1 {
    public static class MyStack<T>{
        public Queue<T> queue;
        public Queue<T> help;

        public T push(T value) {
            if(queue.offer(value)) {
                return value;
            }else {
                return null;
            }
        }

        public T pop(){
            if(queue.isEmpty()) {
                throw new RuntimeException("该栈为空");
            }
            if(queue.size() > 1) {
                help.offer(queue.poll());
            }
            T value = queue.poll();
            pollQueueToHelp(queue,help);
            return value;
        }

        public T peek(){
            if(queue.isEmpty()) {
                throw new RuntimeException("该栈为空");
            }
            if(queue.size() > 1) {
                help.offer(queue.poll());
            }
            T value = queue.poll();
            help.offer(value);
            pollQueueToHelp(queue,help);
            return value;
        }

        private void pollQueueToHelp(Queue<T> queue, Queue<T> help) {
           Queue<T> tmp = queue;
           queue = help;
           help = tmp;
        }
    }
}
