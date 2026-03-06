package gyak4.feladat1;

public class MyThread1 extends Thread {

    public MyThread1() {
        super();
    }
    public MyThread1(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; ++i) {
            fakePrintln(this.getName() + " " + i);
        }
    }

    private static synchronized void fakePrintln(String text) {
        for (int i = 0; i < text.length(); ++i) {
            System.out.print(text.charAt(i));
        }
        System.out.print("\n");
    }
}
