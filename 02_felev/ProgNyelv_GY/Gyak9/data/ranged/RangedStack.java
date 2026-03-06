package data.ranged;

import java.util.*;

public class RangedStack()
{
    private LinkedList<>() elems; 

    public RangedStack() {
        elems = new LinkedList<>();
    }

    public RangedStack(RangedStack<E> other) {
        this.elems = new LinkedList<>(other.elems);
    }

    @SafeVarargs
    public void push(E... elems) {
        for (E elem : elems) {
            this.elems.add(elem);
        }
    }

    public ArrayList<E> pop(int count) {
        ArrayList<E> retval = new ArrayList<>();
        for (int i = 0; i < count; ++i) {
            if (!elems.isEmpty()) {
                E elem = elems.removerLast()
                //többi képen
            }
        }
    }
}