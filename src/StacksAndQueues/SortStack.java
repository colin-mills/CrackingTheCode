package StacksAndQueues;

import java.util.Stack;

/*
3.4 Sort Stack
pg. 99
Sort a stack where the lower values are on top,
with only using one additional stack
 */
public class SortStack<T extends Comparable<T>> extends Stack {
    public void sort (Stack<T> stack) {
        //buffer variable as we load values
        T temp;
        //this will have the bigger values on top
        Stack<T> tempStack = new Stack<>();
        while (!stack.isEmpty()) {
            temp = stack.pop();
            if (!tempStack.isEmpty()) {
                //shift over all the values less than temp back to stack
                while (temp.compareTo(tempStack.peek()) < 0) {
                    stack.push(tempStack.pop());
                }
            }
            tempStack.push(temp);
        }
        //reverse back to original stack
        while (!tempStack.isEmpty()) stack.push(tempStack.pop());
    }
}
