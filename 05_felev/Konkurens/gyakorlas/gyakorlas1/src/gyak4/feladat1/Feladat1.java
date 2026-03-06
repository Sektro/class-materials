package gyak4.feladat1;

public class Feladat1 {
    public static void main(String[] args) {
        String[] names = {"szia", "világ", "másik"};
        for (String n : names) {
            new MyThread1(n).start();
        }
    }
}
