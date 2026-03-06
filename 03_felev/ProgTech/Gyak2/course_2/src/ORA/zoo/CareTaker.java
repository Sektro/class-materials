package ORA.zoo;

import java.util.ArrayList;

/**
 *
 * @author bli
 */
public class CareTaker {
    public ArrayList<Animal> animals;

    public CareTaker() {
        animals = new ArrayList<>();
    }

    public CareTaker(ArrayList<Animal> animals) {
        this.animals = new ArrayList<>(animals);
    }

    public void addAnimal(Animal a) {
        this.animals.add(a);
    }

    public void removeAnimal(Animal a) {
        this.animals.remove(a);
    }

}