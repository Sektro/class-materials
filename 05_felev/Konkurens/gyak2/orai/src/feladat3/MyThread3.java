package feladat3;

public class MyThread3 extends Thread {
    public MyThread3(String name) {
        super();
        this.setName(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; ++i) {
            System.out.println(this.getName() + " " + i);
        }
    }
}
