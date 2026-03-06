package ORA.zoo;

import java.util.ArrayList;

/**
 *
 * @author bli
 */
public class Zoo {

    private ArrayList<Animal> animals;
    private Restaurant restaurant;

    public Zoo() {
        animals = new ArrayList<>();
        restaurant = new Restaurant();
    }

    public Zoo(ArrayList<Animal> animals) {
        this.animals = new ArrayList<>(animals);
    }

    public void addAnimal(Animal a) {
        this.animals.add(a);
    }

    public void removeAnimal(Animal a) {
        this.animals.remove(a);
    }

    public void step() {
        for (Animal a : animals) {
            a.move();
            a.makeSound();
        }
    }
}