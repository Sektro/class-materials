package HF.sikvektor;

public class Vektor {
    private  double x;
    private  double y;
    private double hossz;

    public Vektor(double x, double y) {
        this.x = x;
        this.y = y;
        hossz = hosszSzam();
    }

    public double hosszSzam() {
        return Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
    }
    public boolean equals(Vektor a) {
        return (this.x == a.getX() && this.y == a.getY() && hossz == a.getHossz());
    }

    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }
    public double getHossz()
    {
        return hossz;
    }
}
