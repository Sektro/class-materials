package gyak3.feladat2;

public class MyRunnable2 implements Runnable {
    public MyRunnable2() {
        super();
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; ++i) {
            System.out.println(i);
        }
    }
}
