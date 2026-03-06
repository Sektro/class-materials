package gyak5.feladat2;

public class Feladat2 {
    public static void main(String[] args) {
        int threadNumber = 3;
        int CAPACITY = 5;

        ThreadSafeMutableIntArray array = new ThreadSafeMutableIntArray(CAPACITY);
        Thread[] threads = new Thread[threadNumber];
        for (int i = 0; i < threads.length; ++i) {
            int finalI = i;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < CAPACITY; ++j) {
                        array.set(j, finalI);
                    }
                }
            });
        }
        for (int i = 0; i < threads.length; ++i) {
            threads[i].start();
        }
        for (int j = 0; j < CAPACITY; ++j) {
            System.out.println(array.get(j));
        }
    }
}
