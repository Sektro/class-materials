package ORA.zoo;

/**
 *
 * @author bli
 */
public class Hamster extends Pet {

    @Override
    public void move() {
        System.out.println("Hamster scurries");
    }

    @Override
    public void makeSound() {
        System.out.println("Hamster squeaks");
    }

}