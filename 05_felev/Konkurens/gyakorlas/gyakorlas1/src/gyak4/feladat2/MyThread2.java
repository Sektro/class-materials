package gyak4.feladat2;

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
            synchronized (System.out) {
                fakePrintln(this.getName() + " " + i);
            }
        }
    }

    private static void fakePrintln(String text) {
        for (int i = 0; i < text.length(); ++i) {
            System.out.print(text.charAt(i));
        }
        System.out.print("\n");
    }
}
