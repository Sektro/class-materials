package feladat2;

import java.util.Arrays;

public class ThreadSafeMutableIntArray {

    private int[] array;
    private Object[] locks;

    public ThreadSafeMutableIntArray(int capacity) {
        array = new int[capacity];
        keys = new Object[capacity];
        Arrays.setAll(locks, _ -> new Object()); //olyan, mint egy egyszerű for ciklus
    }

    public final int get(int n) {
        synchronized (locks[n]) {
            return array[n];
        }
    }
    public final synchronized void set(int n, int value) {
        synchronized (locks[n]) {
            array[n] = value;
        }
    }
}
