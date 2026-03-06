package ORA.zoo;

/**
 *
 * @author bli
 */
public class Dog extends Pet {

    @Override
    public void move() {
        System.out.println("Dog runs");
    }

    @Override
    public void makeSound() {
        System.out.println("Dog barks");
    }

}