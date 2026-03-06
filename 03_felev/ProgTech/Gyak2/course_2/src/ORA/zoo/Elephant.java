package ORA.zoo;

/**
 *
 * @author bli
 */
public class Elephant extends WildAnimal {

    @Override
    public void move() {
        System.out.println("Elephant gallops");
    }

    @Override
    public void makeSound() {
        System.out.println("Elephant trumpets");
    }

}