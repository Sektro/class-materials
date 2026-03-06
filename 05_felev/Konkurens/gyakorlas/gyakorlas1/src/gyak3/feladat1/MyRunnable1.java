package gyak3.feladat1;

public class MyRunnable1 implements Runnable {
    public MyRunnable1() {
        super();
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; ++i) {
            System.out.println(i);
        }
    }
}
