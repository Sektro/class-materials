package data.structure;

import java.util.*;

public class MultiSet<E> {
    private HashMap<E, Integer> elemToCount;

    public MultiSet(E[] elems) {
        elemToCount = new HashMap<>();
        for (E elem : elems) {
            add(elem);
        }
    }

    public int add(E elem) {
        /*
        int count = 0;
        if (elemToCount.get(elem) != null) {
            count = elemToCount.get(elem);
        }
        count++;
        */
        int count = elemToCount.getOrDefault(elem, 0); //ez, vagy commentelt rész
        elemToCount.put(elem, count);
        return count;
    }

    public int getCount(E elem) { //java megoldja, hogy intet adjunk integernek (box, unbox)
        return elemToCount.getOrDefault(elem, 0);
    }

    public MultiSet<E> intersect(MeltiSet<E> other) {
        MultiSet<E> retVal = new MultiSet<>();

        for (E key : elemToCount.keySet()) {
            if (!other.elemToCount.containsKey(key)) continue;

            int count1 = elemToCount.get(key);
            int count2 = other.elemToCount.get(key);
            retVal.elemToCount.put(key, Math.min(count1, count2));
        }

        return retVal;
    }

    public int countExcept(Set<E> notCounted) {
        int sum = 0;
        for (Map.Entry<E, Integer> entry : elemToCount.entrySet()) {
            if (notCounted.contains(entry.getKey())) continue;

            sum += entry.getValue();
        }

        return sum;
    }
}