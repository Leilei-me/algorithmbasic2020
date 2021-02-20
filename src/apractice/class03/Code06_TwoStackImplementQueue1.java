package apractice.class03;

import yuanshi.class03.Code06_TwoStacksImplementQueue;

import java.util.Stack;

//辅助栈空了倒数据
//每次倒数据要倒完
public class Code06_TwoStackImplementQueue1 {
    public static class MyQueue{
        Stack<Integer> stackPush;
        Stack<Integer> stackPop;

        MyQueue(){
            stackPush = new Stack<Integer>();
            stackPop = new Stack<Integer>();
        }

        public int add(int value){
            stackPush.push(value);
            return value;
        }

        public int poll(){
            if(! stackPop.empty()){
               return stackPop.pop();
            }else {
                move(stackPush,stackPop);
                return stackPop.pop();
            }
        }

        public int peek(){
            if(! stackPop.empty()){
                return stackPop.peek();
            }else {
                move(stackPush,stackPop);
                return stackPop.peek();
            }
        }

        private void move(Stack<Integer> stackPush, Stack<Integer> stackPop) {
            if(stackPush.empty()) throw new RuntimeException("该队列数据为空");
            while(! stackPush.empty()){
                int temp = stackPush.pop();
                stackPop.push(temp);
            }
        }

        public static void main(String[] args) {
            MyQueue test = new MyQueue();
            test.add(1);
            test.add(2);
            test.add(3);
            System.out.println(test.peek());
            System.out.println(test.poll());
            System.out.println(test.peek());
            System.out.println(test.poll());
            System.out.println(test.peek());
            System.out.println(test.poll());
        }


    }
}
