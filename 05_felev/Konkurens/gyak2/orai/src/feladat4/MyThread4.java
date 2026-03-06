package feladat4;

public class MyThread4 extends Thread {
    public MyThread4(String name) {
        super();
        this.setName(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; ++i) {
            fakePrintln(this.getName() + " " + i);
        }
    }

    public static void fakePrintln(String str) {
        for (int i = 0; i < str.length(); ++i) {
            System.out.print(str.charAt(i));
        }
        System.out.print("\n");
    }
}
