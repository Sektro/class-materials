package sort1;

import java.util.Comparator;

/**
 *
 * @author bli
 */
public class RemainderComparator implements Comparator<Integer> {

    private int n;
    private int sign;

    public RemainderComparator(int n, boolean ascending) {
        this.n = n;
        this.sign = ascending ? 1 : -1;
    }

    @Override
    public int compare(Integer i1, Integer i2) {
        return sign * ((i1 % n) - (i2 % n));
    }
}