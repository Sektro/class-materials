package music.fan;

import music.recording.*;

public class Fan
{
    private final String name;
    private final Artist favourite;
    private int moneySpent;


    /*
    public int buyMerchandise(int cost, Fan[] friends)
    {
        int rebate = 10 * Math.min(friends.length, 2);
        int finalCost = cost * (100 - rebate) / 100;

        favourite.getLabel().gotIncome((friends.length+1) * finalCost/2);
        moneySpent += finalCost;
        for (Fan fan : friends)
        {
            fan.moneySpent += finalCost;
        }
        return finalCost;
    }

    public boolean favesAtSameLabel(Fan otherfan)
    {
        return other.favourite.getLabel() == favourite.getLabel();
    }

    */

    public int buyMerchandise(int cost, Fan[] friends)
    {
        return 0;
    }

    public boolean favesAtSameLabel(Fan otherfan)
    {
        return false;
    }

    public String toString1()
    {
        return "Fan(name=" + name + ", fave= " + favourite.getName() + "@" + favourite.getLabel().getName() + ", spent= " + moneySpent + ")";
    }

    public String toString2()
    {
        return "Fan(name=%s, fave=s@%s, spent=%d)".formatted(name, favourite.getName(), favourite.getLabel().getName(), moneySpent);
    }

    public String toString3()
    {
        return String.format("Fan(name=%s, fave=s@%s, spent=%d)", name, favourite.getName(), favourite.getLabel().getName(), moneySpent);
    }

    public String toString4()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Fan(name= )");
        sb.append(name);
        sb.append(", fave= ");
        sb.append(favourite.getName());
        sb.append("@");
        sb.append(favourite.getLabel().getName());
        sb.append(", spent= ");
        sb.append(moneySpent);
        sb.append(")");
        return sb.toString();
    }

    public Fan(String name, Artist favourite)
    {
        this.name = name;
        this.favourite = favourite;
    }

    public String getName()
    {
        return name;
    }

    public Artist getFavourite()
    {
        return favourite;
    }

    public int getMoneySpent()
    {
        return moneySpent;
    }
}