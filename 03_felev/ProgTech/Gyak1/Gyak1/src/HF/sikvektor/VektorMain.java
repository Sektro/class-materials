package HF.sikvektor;

public class VektorMain {

    public static void main(String[] args) {
        Vektor a = new Vektor(1,0);
        Vektor b = new Vektor(0,1);

        System.out.println("A skaláris szorzata a két vektornak (1.megoldás) = " + skalaris1(a,b));
        System.out.println("A skaláris szorzata a két vektornak (2.megoldás) = " + skalaris2(a.getHossz(), b.getHossz(), 90));
        if (a.equals(b))
        {
            System.out.println("A két vektor egyenlő");
        }
        else {
            System.out.println("A két vektor nem egyenlő");
        }
    }

    public static double skalaris1(Vektor a, Vektor b) {return ((a.getX() * b.getX()) + (a.getY() * b.getY()));}
    public static double skalaris2(double a, double b, double angle) {
        double radian = Math.toRadians(angle);
        return (a * b * Math.cos(radian));
    }
}
