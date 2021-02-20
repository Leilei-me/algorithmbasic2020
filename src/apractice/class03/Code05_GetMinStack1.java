package apractice.class03;

import java.util.Stack;

//一种是两个栈每次都push，每次都pop
//另一种是min栈只有遇到最小值时才push或pop
public class Code05_GetMinStack1 {
    public static class MinStack{
        public Stack<Integer> dataStack;
        public Stack<Integer> minStack;

        public int push(int value){
            dataStack.push(value);
            if(dataStack.isEmpty()){
                minStack.push(value);
            }else {
                int peek = minStack.peek();
                if(peek <= value) {
                    minStack.push(peek);
                }else {
                    minStack.push(value);
                }
            }
            return value;
        }

        public int pop(){
            if(dataStack.isEmpty()) {
                throw new NullPointerException();
            }
            minStack.pop();
            return dataStack.pop();
        }

        public int getMin() {
            if(dataStack.isEmpty()) {
                throw new NullPointerException();
            }
            return minStack.peek();
        }

        public int peek(){
            if(dataStack.isEmpty()) {
                throw new NullPointerException();
            }
            return dataStack.peek();
        }
    }
}
