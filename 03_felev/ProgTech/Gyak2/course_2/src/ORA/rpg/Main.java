package ORA.rpg;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args)
    {
        ArrayList<Character> enemies = new ArrayList<Character>();
        enemies.add(new DragonBlack("Kadregin the Eternal", 200, 50));
        enemies.add(new OrkBerserk("Gork", 20, 40));
        enemies.add(new OrkShield("Gronk", 40, 20));
        Character hero = new MainCharacter("John",10,21,600);

        if (War(hero,enemies))
        {
            System.out.println("The main character lost!");
        }
        else
        {
            System.out.println("The main character won!");
        }
    }

    public static void Fight(Character c1, Character c2) {
        c1.Attack(c2);
        c2.Attack(c1);
    }
    public static boolean allDead(ArrayList<Character> characters)
    {
        for (Character elem : characters)
        {
            if (!elem.isDead()) {return false;}
        }
        return true;
    }
    public static boolean War(Character hero, ArrayList<Character> enemies)
    {
        while (!hero.isDead() || allDead(enemies))
        {
            for (Character enemy : enemies)
            {
                Fight(hero,enemy);
            }
        }
        return hero.isDead();
    }
}
