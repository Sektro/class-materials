package gyak3.feladat1;

public class Feladat1 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyRunnable1());
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100000; ++i) {
                System.out.println(i);
                System.out.println(-i);
            }
        });

        t1.start();
        t2.start();
    }
}
