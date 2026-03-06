package gyak2.feladat1;

public class MyThread1 extends Thread {
    public MyThread1() {
        super();
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; ++i) {
            System.out.println(i);
        }
    }
}
