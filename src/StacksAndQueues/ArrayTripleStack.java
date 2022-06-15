package StacksAndQueues;

import java.security.InvalidParameterException;
import java.util.EmptyStackException;

/*
3.1 Three in one
pg. 98
 */
public class ArrayTripleStack {

    //Stack Variables
    int[] stack;
    StackInfo[] metadata;

    //metadata about each stack
    private class StackInfo{
        int startIndex;
        int size;
        int capacity;

        StackInfo(int startIndex, int capacity) {
            this.startIndex = startIndex;
            this.capacity = capacity;
            this.size = 0;
        }

        public int getTopIndex() {return startIndex + size;}
        public boolean isFull() {return size == capacity;}
        public boolean isEmpty() {return size == 0;}
    }

    /****************
     * Constructors *
     ***************/

    ArrayTripleStack() {
        this.stack = new int[27];
        initializeStackInfo(27);
    }

    ArrayTripleStack(int capacity) {
        this.stack = new int[capacity];
        initializeStackInfo(capacity);
    }

    private void initializeStackInfo(int capacity) {
        int extraSpace = capacity % 3, leftOvers, start = 0;
        this.metadata = new StackInfo[3];

        for (int i = 0; i < 3; i++) {
            leftOvers = extraSpace-- > 0 ? 1 : 0;
            this.metadata[i] = new StackInfo(start, capacity/3 + leftOvers);
            start += this.metadata[i].capacity;
        }
    }

    /*******************
     *  Public Methods *
     *******************/
    public void push(int stackNum, int val) {
        if (stackNum < 0 || stackNum > 3) throw new InvalidParameterException("Must be within 0-2");
        if (this.metadata[stackNum].isFull()) expandStacks();
        else if (metadata[stackNum].isEmpty()) stack[metadata[stackNum].startIndex] = val;
        else {
            metadata[stackNum].size++;
            stack[metadata[stackNum].getTopIndex()] = val;
        }
    }

    public int pop(int stackNum) {
        int res = peek(stackNum);
        metadata[stackNum].size--;
        return res;
    }

    public int peek(int stackNum) {
        if (stackNum < 0 || stackNum > 3) throw new InvalidParameterException("Must be within 0-2");
        if (metadata[stackNum].isEmpty()) throw new EmptyStackException();
        return stack[metadata[stackNum].getTopIndex()];
    }

    /*******************
     * Private Methods *
     *******************/
    private void expandStacks() {

    }

}
