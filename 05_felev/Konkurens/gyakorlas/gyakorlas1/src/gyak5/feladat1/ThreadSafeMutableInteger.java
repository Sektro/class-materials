package gyak5.feladat1;

public class ThreadSafeMutableInteger {

    private int value;
    public final synchronized int getValue() {
        return value;
    }
    public final synchronized void setValue(int value) {
        this.value = value;
    }

    public ThreadSafeMutableInteger() {
        value = 0;
    }
    public ThreadSafeMutableInteger(int value) {
        this.value = value;
    }

    public final synchronized int getAndIncrement() {
        return value++;
    }
    public final synchronized int getAndDecrement() {
        return value--;
    }
    public final synchronized int getAndAdd(int v) {
        int temp = value;
        value += v;
        return temp;
    }
    public final synchronized int incrementAndGet() {
        return ++value;
    }
    public final synchronized int decrementAndGet() {
        return --value;
    }
    public final synchronized int addAndGet(int v) {
        return value += v;
    }
}
