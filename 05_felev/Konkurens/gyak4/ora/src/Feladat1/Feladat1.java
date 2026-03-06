package Feladat1;

public class Feladat1 {
    public static void main(String[] args) {
        String[] str = {"1. szál", "2. szál", "3. szál"};
        for (int i = 0; i < str.length; ++i) {
            new MyThread1(str[i]).start();
        }
    }
}