package gyak3.feladat3;

public class Feladat3 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyRunnable3());
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; ++i) {
                System.out.println(i);
                System.out.println(-i);
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
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
