package feladat1;

public class ThreadSafeMutableInteger {

    private int value;

    public ThreadSafeMutableInteger() {
        value = 0;
    }
    public ThreadSafeMutableInteger(int value) {
        this.value = value;
    }

    public final synchronized int get() {
        return value;
    }
    public final synchronized void set(int value) {
        this.value = value;
    }

    public final synchronized int getAndIncrement() {
        return value++;
    }
    public final synchronized int getAndDecrement() {
        return value--;
    }
    public final synchronized int getAndAdd(int v) {
        int x = value;
        value += v;
        return x;
    }
    public final synchronized int incrementAndGet() {
        return ++value;
    }
    public final synchronized int decrementAndGet() {
        return --value;
    }
    public final synchronized int addAndGet(int v) {
        value += v;
        return value;
    }

    /*
        incrementre nem jó: n.set(n.get() + 1) ez nincs szinkronizálva
    */
}
