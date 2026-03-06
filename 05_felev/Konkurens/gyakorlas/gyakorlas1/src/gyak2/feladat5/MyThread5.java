package gyak2.feladat5;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class MyThread5 extends Thread {

    public MyThread5() {
        super();
    }
    public MyThread5(String name) {
        super(name);
    }

    @Override
    public void run() {
        PrintWriter out = null;
        try {
            out = new PrintWriter("src/gyak2/feladat5/output.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < 100000; ++i) {
            out.println(this.getName() + " " + i);
        }
        out.flush();
        out.close();
    }
}
