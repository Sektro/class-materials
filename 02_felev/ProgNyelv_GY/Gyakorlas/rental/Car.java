package rental;

public class Car
{
    private String brand;
    private String licensePlate;
    private double price; 

    public double getPrice() {
        return price;
    }

    private static final double MAX_PRICE = 500.0;
    private static final Car CAR_OF_THE_YEAR = new Car("Alfa Romeo","ABC 123",MAX_PRICE);

    private Car(String brand, String licensePlate, double price)
    {
        this.brand = brand;
        this.licensePlate = licensePlate;
        this.price = price;
    }

    
    public boolean isCheaperThan(Car other) {
        return price < other.getPrice();
    }
    public static Car make(String brand, String licensePlate, double price) {
        //I
        if (brand.length() < 2) {return null;}
        for (char item : brand.toCharArray()) {
            if (!Character.isLetter(item) && item != ' ') {
                return null;
            }
        }
        if (!isValidLicensePlate(licensePlate)) {return null;}
        if (price <= 0 || price > MAX_PRICE) {return null;}
        //II
        return new Car(brand, licensePlate, price);
    }
    private static boolean isValidLicensePlate(String licensePlate) {
        if (licensePlate.length() == 7) {return false;}
        int i = 0;
        for (char item : licensePlate.toCharArray()) {
            if (!Character.isLetter(item) && i < 3) {
                return false;
            }
            if (item != ' ' && i == 3) {
                return false;
            }
            if (i > 3) {
                try {
                    int j = (int)item; // if (!Characte.isDigit(licensePlate.charAt(i)))
                } catch (Exception e) {
                    return false;
                }
            }
            ++i;
        }
        return true;
    }
    public void decreasePrice() {
        if (price > 10 && price != MAX_PRICE)
            price -= 10;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        double mamad = Math.floor(price * 10);
        mamad = mamad / 10;
        sb.append(brand);
        sb.append(" (" + licensePlate + ") ");
        sb.append(mamad + " EUR ");

        return sb.toString();
    }
}