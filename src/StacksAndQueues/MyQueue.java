package StacksAndQueues;

import java.util.EmptyStackException;
import java.util.Stack;

/*
3.4 Queue Via Stacks
pg. 99
Create a queue using two stacks
 */
public class MyQueue <T> {
    Stack<T> stack = new Stack<>();
    Stack<T> queue = new Stack<>();

    public void push(T val) {
        //doesn't matter if there are values in queue or not
        //because we will just load more in the correct order
        //when queue becomes empty
        stack.push(val);
    }

    public T peek() {
        if (queue.isEmpty()) {
            if (stack.isEmpty()) throw new EmptyStackException();
            else refreshQueue();
        }
        return queue.peek();
    }

    public T pop() {
        if (queue.isEmpty()) {
            if (stack.isEmpty()) throw new EmptyStackException();
            else refreshQueue();
        }
        return queue.peek();
    }

    private void refreshQueue() {
        while (!stack.isEmpty()) queue.push(stack.pop());
    }
 }
