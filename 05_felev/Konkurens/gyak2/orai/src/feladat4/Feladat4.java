package feladat4;

public class Feladat4 {
    public static void main(String[] args) {
        String[] str = {"1. szál", "2. szál", "3. szál"};
        for (int i = 0; i < str.length; ++i) {
            new MyThread4(str[i]).start();
        }
    }
}
