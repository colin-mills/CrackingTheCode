package StacksAndQueues;

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

}
