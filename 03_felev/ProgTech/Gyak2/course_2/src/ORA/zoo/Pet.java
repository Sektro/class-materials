package ORA.zoo;

/**
 *
 * @author bli
 */
public abstract class Pet extends Animal {

    @Override
    public void move() {
        System.out.println("Pet moves lazily");
    }

}