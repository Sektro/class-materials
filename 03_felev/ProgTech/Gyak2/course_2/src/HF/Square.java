package HF;

public class Square implements Shape {
    public double side;

    public Square(double side) {
        this.side = side;
    }

    public double Area() {
        return side * side;
    }
}
