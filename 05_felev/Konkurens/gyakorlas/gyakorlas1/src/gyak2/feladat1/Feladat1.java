package gyak2.feladat1;

public class Feladat1 {
    public static void main(String[] args) {
        MyThread1 t1 = new MyThread1();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; ++i) {
                    System.out.println(i);
                    System.out.println(-i);
                }
            }
        });

        t1.start();
        t2.start();
    }
}
