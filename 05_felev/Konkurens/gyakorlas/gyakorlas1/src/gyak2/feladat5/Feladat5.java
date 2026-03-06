package gyak2.feladat5;

import gyak2.feladat2.MyThread2;

public class Feladat5 {
    public static void main(String[] args) {
        String[] names = {"szia", "világ", "másik"};
        for (String n : names) {
            new MyThread5(n).start();
        }
    }
}
