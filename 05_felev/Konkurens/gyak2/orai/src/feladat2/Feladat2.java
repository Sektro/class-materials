package feladat2;

public class Feladat2 {
    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 1; i <= 100000; ++i) {
                System.out.println("szia " + i);
            }
        }).start();
        new Thread(() -> {
            for (int i = 1; i <= 100000; ++i) {
                System.out.println("világ " + i);
            }
        }).start();
        new Thread(() -> {
            for (int i = 1; i <= 100000; ++i) {
                System.out.println("másik " + i);
            }
        }).start();
    }
}
