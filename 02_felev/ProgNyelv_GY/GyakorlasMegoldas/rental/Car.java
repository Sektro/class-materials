package rental;

public class Car {
    private String brand;
    private String licensePlate;
    private double price;

    public double getPrice() {
        return price;
    }

    private final static double MAX_PRICE = 500.0;
    private final static Car CAR_OF_THE_YEAR = new Car("Alfa Romeo", "ABC 123", MAX_PRICE);

    private Car(String brand, String licensePlate, double price) {
        this.brand = brand;
        this.licensePlate = licensePlate;
        this.price = price;
    }

    public boolean isCheaperThan(Car other) {
        return price < other.price;
    }

    public void decreasePrice() {
        if (price > 10 && price != MAX_PRICE) {
            price -= 10;
        }
    }

    public static Car make(String brand, String licensePlate, double price) {
        if (brand.length() < 2) return null;
        for (char c : brand.toCharArray()) {
            if (!Character.isLetter(c) && c != ' ') {
                return null;
            }
        }

        if (!isValidLicensePlate(licensePlate)) return null;
        if (price <= 0 || price > MAX_PRICE) return null;

        return new Car(brand, licensePlate, price);
    }

    private static boolean isValidLicensePlate(String licensePlate) {
        if (licensePlate.length() != 7) return false;
        for (int i = 0; i < 3; ++i) {
            if (!Character.isUpperCase(licensePlate.charAt(i))) {
                return false;
            }
        }

        if (licensePlate.charAt(3) != ' ') {
            return false;
        }

        for (int i = 4; i < 7; ++i) {
            if (!Character.isDigit(licensePlate.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(brand);
        sb.append(" (");
        sb.append(licensePlate);
        sb.append(") ");
        if (price < 10) {
            sb.append("  ");
        } else if (price < 100) {
            sb.append(" ");
        }
        sb.append(price);
        sb.append(" EUR");
        return sb.toString();
    }
}