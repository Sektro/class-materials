package HF;

public class Circle implements Shape {
    public double r;

    public Circle(double r) {
        this.r = r;
    }

    public double Area() {
        return r * r * Math.PI;
    }
}
