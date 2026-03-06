package HF;

public class Pentagon {
    public static void main(String[] args)
    {
        Feladat();
    }

    public static void Feladat()
    {
        int[] pentagons = new int[30];

        for (int i = 0; i < pentagons.length; ++i)
        {
            pentagons[i] = Pentagon((double)(i+1));
        }
        for (int i = 0; i < pentagons.length; ++i)
        {
            System.out.print(pentagons[i] + " ");
        }
    }

    public static int Pentagon(double n)
    {
        return (int)((3 * Math.pow(n,2) - n) / 2);
    }
}
