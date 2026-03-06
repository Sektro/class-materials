package rental;

import java.io.*;
import java.util.*;

public class CarRental 
{
    private ArrayList<Car> cars;

    public CarRental(String filename)
    {
        cars = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename)))
        {
            String line;
            while (((line = br.readLine()) != null))
            {
                try {
                    String[] elso = line.split(":");
                    String brand = elso[0];
                    String[] masodik = elso[1].split(",");
                    String licensePlate = masodik[0];
                    double price = Double.parseDouble(masodik[1]);
                    Car c = Car.make(brand, licensePlate, price);
                    if (c != null) {cars.add(c);} 
                } catch (NumberFormatException e) {
                } catch (Exception e) {
                }
                
            }
            
        } catch (IOException e) {
        }
    }

    public int numberOfCars() {
        return cars.size();
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
