package HF;

import java.io.*;

public class Pythagoras {
    public static void main(String[] args) //try-catch nélkül csak ide: throws IOException
    {
        boolean works = false;
        String[] numbers = new String[3];
        while (!works)
        {
            try {
                System.out.print("Adjon meg három számot (szóközzel elválasztva): ");
                BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
                numbers = buffReader.readLine().split(" ");
                works = true;
            } catch (IOException e)
            {
                System.out.println("Valami nem stimmel...");
                works = false;
            }
        }


        int a = Integer.parseInt(numbers[0]);
        int b = Integer.parseInt(numbers[1]);
        int c = Integer.parseInt(numbers[2]);

        System.out.println(a + " " + b + " " + c);

        if (isPythagoras(a,b,c))
        {
            System.out.println("A megadott számok egy Pitagoraszi számhármas!");
        }
        else
        {
            System.out.println("A megadott számok NEM egy Pitagoraszi számhármas!");
        }
    }

    public static boolean isPythagoras(int a, int b, int c)
    {
        return (Math.pow((double)a,2) + Math.pow((double)b,2) == Math.pow((double)c,2)) || (Math.pow((double)a,2) + Math.pow((double)c,2) == Math.pow((double)b,2)) || (Math.pow((double)b,2) + Math.pow((double)c,2) == Math.pow((double)a,2));
    }
}
