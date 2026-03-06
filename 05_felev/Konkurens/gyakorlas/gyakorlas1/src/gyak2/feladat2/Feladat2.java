package gyak2.feladat2;

public class Feladat2 {
    public static void main(String[] args) {
        String[] names = {"szia", "világ", "másik"};
        for (String n : names) {
            new MyThread2(n).start();
        }
    }
}
