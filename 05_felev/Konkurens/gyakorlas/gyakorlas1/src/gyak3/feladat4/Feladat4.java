package gyak3.feladat4;

public class Feladat4 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyRunnable4());
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; ++i) {
                System.out.println(i);
                System.out.println(-i);
            }
        });
        t1.start();
        t2.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        t1.interrupt();
        t2.interrupt();
        t1.start();
        t2.start();
    }
}
