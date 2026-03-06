package gyak2.feladat4;

public class Feladat4 {
    public static void main(String[] args) {
        String[] names = {"szia", "világ", "másik"};
        for (String n : names) {
            new MyThread4(n).start();
        }
    }
}
