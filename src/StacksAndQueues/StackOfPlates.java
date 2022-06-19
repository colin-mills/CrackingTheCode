package StacksAndQueues;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class StackOfPlates <T>  {
    public List<Stack<T>> stacks = new ArrayList<>();
    public int maxHeight = 10;
    public int frontIndex = 0;

    StackOfPlates (int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public void push (T val) {
        if (stacks.get(frontIndex).size() >= maxHeight) {
            stacks.add(new Stack<>());
            frontIndex++;
        }
        stacks.get(frontIndex).push(val);
    }

    public T pop() {
        return popAt(frontIndex);
    }

    public T popAt(int index) {
        if (index < 0 || index >= stacks.size()) throw new ArrayIndexOutOfBoundsException();
        if (stacks.get(index).isEmpty()) {
            if (index == 0) throw new EmptyStackException();
            else {
                //remove empty index if front index, otherwise we will just leave it
                if (frontIndex == index) stacks.remove(frontIndex--);
                //and then call pop at again to re-run bounds checking
                return popAt(index-1);
            }
        }
        return stacks.get(frontIndex).pop();
    }

}
