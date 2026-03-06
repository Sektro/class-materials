package music.recording;

public class RecordLabel
{
    private final String name; // final: nem módosítható
    private int cash;

    public RecordLabel(String name, int cash)
    {
        this.name = name;
        this.cash = cash;
    }

    public String getName()
    {
        return name;
    }

    public int getCash()
    {
        return cash;
    }

    public void gotIncome(int sum)
    {
        cash += sum;
    }
}