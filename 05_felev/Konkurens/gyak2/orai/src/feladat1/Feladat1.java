package feladat1;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Feladat1 {
    public static void main(String[] args) {
        new Thread(() -> {
            PrintWriter output1;
            try {
                output1 = new PrintWriter("output.txt");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            for (int i = 1; i <= 100; ++i) {
                System.out.println(i);
                output1.println(i);
            }
            output1.close();
        }).start();
        new Thread(() -> {
            PrintWriter output2;
            try {
                output2 = new PrintWriter("output.txt");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            for (int i = 1; i <= 100; ++i) {
                System.out.println(i);
                System.out.println(-i);
                output2.println(i);
            }
            output2.close();
        }).start();

        // több magon szétosztás attól függ, hogy mennyire intenziv (ez nem az, egy ideig blokkol, aztán váltogat)
    }
}
