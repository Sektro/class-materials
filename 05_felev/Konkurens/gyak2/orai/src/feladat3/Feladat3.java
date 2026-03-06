package feladat3;

public class Feladat3 {
    public static void main(String[] args) {
        String[] str = {"1. szál", "2. szál", "3. szál"};
        for (int i = 0; i < str.length; ++i) {
            new MyThread3(str[i]).start();
        }
    }
}
