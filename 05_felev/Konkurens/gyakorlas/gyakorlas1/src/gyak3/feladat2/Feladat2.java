package gyak3.feladat2;

public class Feladat2 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyRunnable2());
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100000; ++i) {
                System.out.println(i);
                System.out.println(-i);
            }
        });
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Kész!");
    }
}
