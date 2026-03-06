import javax.swing.*;
import java.awt.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class FractalTree extends Canvas {
    /* Variables with class-wide visibility */
    private static boolean slowMode;

    private static class Params {

        private int x;
        private int y;
        private int x2;
        private int y2;
        private Color color;

        private Params(Color color, int x,int y,int x2,int y2) {
            this.color = color;
            this.x = x;
            this.y = y;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    public ExecutorService pool = Executors.newFixedThreadPool(128);
    public final LinkedBlockingQueue lines = new LinkedBlockingQueue<Params>(1024);
    public AtomicInteger counter = new AtomicInteger(0);


    /* Recursive function for calculating all drawcalls for the fractal tree */
    public void makeFractalTree(int x, int y, int angle, int height) throws InterruptedException {


        if (slowMode) {
            try {Thread.sleep(100);}
            catch (InterruptedException ie) {ie.printStackTrace();}
        }

        if (height == 0) return;

        int x2 = x + (int)(Math.cos(Math.toRadians(angle)) * height * 8);
        int y2 = y + (int)(Math.sin(Math.toRadians(angle)) * height * 8);

        Color color;
        if (height < 5 ) color = Color.GREEN;
        else color = Color.BLACK;

        Params params = new Params(color, x, y, x2, y2);
        lines.put(params);

        pool.submit(() -> {
            counter.incrementAndGet();
            try {
                makeFractalTree(x2, y2, angle-20, height-1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            counter.decrementAndGet();
            synchronized (this) {
                this.notify();
            }
        });
        pool.submit(() -> {
            counter.incrementAndGet();
            try {
                makeFractalTree(x2, y2, angle+20, height-1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            counter.decrementAndGet();
            synchronized (this) {
                this.notify();
            }
        });
    }

    /* Code for EDT */
    /* Must only contain swing code (draw things on the screen) */
    /* Must not contain calculations (do not use math and compute libraries here) */
    /* No need to understand swing, a simple endless loop that draws lines is enough */
    @Override
    public void paint(Graphics g) {
        while (true) {
            Params params = (Params) lines.poll();
            if (params != null) {
                g.setColor(params.color);
                g.drawLine(params.x, params.y, params.x2, params.y2);
                try {
                    lines.put(params);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /* Code for main thread */
    public static void main(String args[]) throws InterruptedException {

        /* Parse args */
        slowMode = args.length != 0 && Boolean.parseBoolean(args[0]);

        /* Initialize graphical elements and EDT */
        FractalTree tree = new FractalTree();
        JFrame frame = new JFrame();
        frame.setSize(800,600);
        frame.setVisible(true);
        frame.add(tree);

        tree.pool.submit(() -> {
            tree.counter.incrementAndGet();
            try {
                tree.makeFractalTree(390, 480, -90, 10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            tree.counter.decrementAndGet();
            synchronized (tree) {
                tree.notify();
            }
        });

        while (true) {
            synchronized (tree) {
                tree.wait();
            }
            Thread.sleep(1000);
            if (tree.counter.get() == 0) {
                break;
            }
        }
        tree.pool.shutdown();
        tree.pool.awaitTermination(1, TimeUnit.MINUTES);


        /* Log success as last step */
        System.out.println("Main has finished");
    }
}


