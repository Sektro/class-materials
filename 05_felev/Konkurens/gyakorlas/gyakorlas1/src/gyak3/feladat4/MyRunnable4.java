package gyak3.feladat4;

public class MyRunnable4 implements Runnable {
    public MyRunnable4() {
        super();
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; ++i) {
            System.out.println(i);
        }
    }
}
