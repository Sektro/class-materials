package rental;

import java.util.*;
import java.io.*;

public class CarRental {
    private ArrayList<Car> cars;

    public CarRental(String filename) {
        cars = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    String[] splitByColon = line.split(":");
                    String brand = splitByColon[0];
                    // System.out.println("brand: " + brand);
                    String[] splitByComma = splitByColon[1].split(",");
                    String licensePlate = splitByComma[0];
                    // System.out.println("licensePlate: " + licensePlate);
                    double price = Double.parseDouble(splitByComma[1]);
                    // System.out.println("price: " + price);
                    Car c = Car.make(brand, licensePlate, price);
                    if (c != null) cars.add(c);
                } catch (NumberFormatException e) {
                    // Empty
                } catch (Exception e) {
                    // Empty
                }
            }
        } catch (IOException e) {
            // Empty
        }
    }
    public int numberOfCars() {
        return cars.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Car c : cars) {
            sb.append(c);
            sb.append('\n');
        }
        return sb.toString();
    }

    public void insertionSort() {
        for (int i = 1; i < numberOfCars(); ++i) {
            Car c = cars.get(i);
            for (int j = 0; j < i; ++j) {
                if (c.isCheaperThan(cars.get(j))) {
                    for (int k = i; k > j; --k) {
                        cars.set(k, cars.get(k - 1));
                    }
                    cars.set(j, c);
                }
            }
        }
    }

    public double weightedAverage() {
        if (numberOfCars() == 0) return -1.0;
        double avg = 0;
        double weightSum = 0;
        for (int i = 0; i < numberOfCars(); ++i) {
            avg += cars.get(i).getPrice() * (i + 1);
            weightSum += (i + 1);
        }
        return avg / weightSum;
    }

    public Car rentCheapest() {
        if (numberOfCars() == 0) return null;
        insertionSort();
        Car c = cars.get(0);
        cars.remove(c);
        return c;
    }

    public ArrayList<Car> sale() {
        ArrayList<Car> sale = new ArrayList<>();
        Random r = new Random();
        for (Car c : cars) {
            if (r.nextInt(10) < 5) {
                c.decreasePrice();
            }
            sale.add(c);
        }
        return sale;
    }
}