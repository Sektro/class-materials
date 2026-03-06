package HF;

import java.util.Random;

public class Tomb {
    public static void main(String[] args)
    {
        Feladat1();
        Feladat2();
    }

    static void Feladat1()
    {
        int[] tomb = new int[15];
        Random rnd = new Random();
        // 2. double random = Math.random() * 24 + 1;
        // 3. int random = (int)(Math.random() * 25 + 1);

        for (int i = 0; i < tomb.length; ++i) { tomb[i] = rnd.nextInt(25) + 1; }
        int current;
        boolean van = false;
        for (int i = 0; i < tomb.length && !van; ++i)
        {
            current = tomb[i];
            for (int j = 0; j < tomb.length && !van; ++j)
            {
                if (current == tomb[j] && j != i) {van = true;}
            }
        }

        if (van)
        {
            System.out.println("1. A tömbben van két egyforma elem");
        }
        else
        {
            System.out.println("1. A tömbben nincs két egyforma elem");
        }
        System.out.print("(a tömb: ) ");
        for (int j = 0; j < tomb.length; ++j)
        {
            if (j < tomb.length-1) {System.out.print(tomb[j] + ", ");}
            else {System.out.println(tomb[j]);}
        }
    }


    static void Feladat2()
    {
        int[] tomb = new int[50];
        Random rnd = new Random();
        // 2. double random = Math.random() * 24 + 1;
        // 3. int random = (int)(Math.random() * 25 + 1);

        for (int i = 0; i < tomb.length; ++i) { tomb[i] = rnd.nextInt(25) + 1; }
        int current;
        boolean van = false;
        for (int i = 0; i < tomb.length && !van; ++i)
        {
            current = tomb[i];
            for (int j = 0; j < tomb.length && !van; ++j)
            {
                if (current == tomb[j] && j != i) {van = true;}
            }
        }

        if (van)
        {
            System.out.println("2. A tömbben van két egyforma elem");
        }
        else
        {
            System.out.println("2. A tömbben nincs két egyforma elem");
        }
        System.out.print("(a tömb: ) ");
        for (int j = 0; j < tomb.length; ++j)
        {
            if (j < tomb.length-1) {System.out.print(tomb[j] + ", ");}
            else {System.out.println(tomb[j]);}
        }
    }
}
