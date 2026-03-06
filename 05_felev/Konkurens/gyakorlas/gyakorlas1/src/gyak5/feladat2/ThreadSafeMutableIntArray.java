package gyak5.feladat2;

public class ThreadSafeMutableIntArray {
    private int[] array;
    private Object[] locks;

    public final int length;

    public ThreadSafeMutableIntArray(int capacity) {
        array = new int[capacity];
        locks = new Object[capacity];
        length = array.length;
        for (int i = 0; i < length; i++) {
            locks[i] = new Object();
        }
    }

    public int get(int n) {
        synchronized (locks[n]) {
            return array[n];
        }
    }
    public void set(int n, int value) {
        synchronized (locks[n]) {
            array[n] = value;
        }
    }
}
