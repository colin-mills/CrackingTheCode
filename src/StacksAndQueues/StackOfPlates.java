package StacksAndQueues;

import java.util.ArrayList;
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


}
