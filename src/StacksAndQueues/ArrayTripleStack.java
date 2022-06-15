package StacksAndQueues;

import java.security.InvalidParameterException;
import java.util.EmptyStackException;

/*
3.1 Three in one
pg. 98
 */
public class ArrayTripleStack {
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

    //Stack Variables
    int[] stack;
    StackInfo[] metadata;

    ArrayTripleStack() {
        this.stack = new int[27];
        this.metadata = new StackInfo[3];
        initializeStackInfo(27, this.metadata);
    }

    ArrayTripleStack(int capacity) {
        this.stack = new int[capacity];
        this.metadata = new StackInfo[3];
        initializeStackInfo(capacity, this.metadata);
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

    public boolean isEmpty(int stackNum) {
        if (stackNum < 0 || stackNum > 3) throw new InvalidParameterException("Must be within 0-2");
        return metadata[stackNum].isEmpty();
    }

    /*******************
     * Private Methods *
     *******************/
    private void initializeStackInfo(int capacity, StackInfo[] meta) {
        int extraSpace = capacity % 3, leftOvers, start = 0;
        this.metadata = new StackInfo[3];

        for (int i = 0; i < 3; i++) {
            leftOvers = extraSpace-- > 0 ? 1 : 0;
            this.metadata[i] = new StackInfo(start, capacity/3 + leftOvers);
            start += this.metadata[i].capacity;
        }
    }

    private void expandStacks() {
        int capacity = stack.length * 2;
        int[] newStack = new int[capacity];
        StackInfo[] newMeta = new StackInfo[3];
        initializeStackInfo(capacity, newMeta);

        for (int i = 2; i >= 0; i--) {
            newMeta[i].size = metadata[i].size;
            while (!metadata[i].isEmpty()) {
                //copy over old values
                newStack[metadata[i].startIndex + metadata[i].size -1] = metadata[i].getTopIndex();
                metadata[i].size--;
            }
        }

    }

}
