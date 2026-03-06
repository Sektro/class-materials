package gyak2.feladat2;

public class MyThread2 extends Thread {
    public MyThread2() {
        super();
    }
    public MyThread2(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; ++i) {
            System.out.println(this.getName() + " " + i);
        }
    }
}
