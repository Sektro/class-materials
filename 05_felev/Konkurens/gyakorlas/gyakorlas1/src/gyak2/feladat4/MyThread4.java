package gyak2.feladat4;

public class MyThread4 extends Thread {

    public MyThread4() {
        super();
    }
    public MyThread4(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; ++i) {
            fakePrintln(this.getName() + " " + i);
        }
    }

    private void fakePrintln(String text) {
        for (int i = 0; i < text.length(); ++i) {
            System.out.print(text.charAt(i));
        }
        System.out.print("\n");
    }
}
