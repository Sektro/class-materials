package ORA.pelda;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bli
 */
public abstract class Vehicle {

    String plate;
    String category;
    List<Integer> refuels;

    public Vehicle(String plate, String category) {
        this.plate = plate;
        this.category = category;
        refuels = new ArrayList<>();
    }

    public void addRefuel(int amount) {
        refuels.add(amount);
    }

    public Double sumRefuels() {
        double sum = 0;
        for (int refuel : refuels) {
            sum += refuel;
        }
        return sum;
    }

    public Integer numRefuels() {
        return refuels.size();
    }

    public Double meanRefuels() {
        return sumRefuels() / numRefuels();
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Vehicle{" + "plate=" + plate + ", category=" + category + ", refuels=" + refuels + '}';
    }
}