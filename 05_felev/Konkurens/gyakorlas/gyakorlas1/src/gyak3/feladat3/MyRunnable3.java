package gyak3.feladat3;

public class MyRunnable3 implements Runnable {
    public MyRunnable3() {
        super();
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; ++i) {
            System.out.println(i);
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
