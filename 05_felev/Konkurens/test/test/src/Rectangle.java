import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Rectangle {
    public static void main(String[] args) {
        // input for the coordinates of the rectangle
        int[][] rectangleCoordinates = new int[4][2];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 4; ++i) {
            try {
                System.out.println("Enter a rectangle coordinate (x):");
                int x = Integer.parseInt(br.readLine());
                System.out.println("Enter a rectangle coordinate (y):");
                int y = Integer.parseInt(br.readLine());
                rectangleCoordinates[i] = new int[]{x,y};
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        // input for the rest of the coordinates
        ArrayList<Integer> coordinates = new ArrayList<Integer>();
        boolean done = false;
        while (!done) {
            try {
                int x = 0;
                int y = 0;
                System.out.println("Enter a coordinate (x):");
                String input = br.readLine();
                if (input.equals("")) {
                    done = true;
                }
                else {
                    x = Integer.parseInt(input);
                }
                if (!done) {
                    System.out.println("Enter a coordinate (y):");
                    input = br.readLine();
                    if (input.equals("")) {
                        done = true;
                    }
                    else {
                        y = Integer.parseInt(input);
                    }
                }
                if (!done) {
                    coordinates.add(x);
                    coordinates.add(y);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        // the rest of the code isn't finished, so this is just to show that the inputs work
        for (int i = 0; i < rectangleCoordinates.length; ++i) {
            for (int j = 0; j < rectangleCoordinates[i].length; ++j) {
                System.out.println(rectangleCoordinates[i][j]);
            }
        }
        for (int c : coordinates) {
            System.out.println(c);
        }
    }
}

