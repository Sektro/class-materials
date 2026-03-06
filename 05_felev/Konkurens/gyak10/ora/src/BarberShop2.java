import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BarberShop2 {
    private static final int WAITING_ROOM_SIZE = 5;
    private static final int MAX_CUSTOMERS = 30;

    private static Set<Customer> waitingRoom = new HashSet<>();
    private static boolean open = true;

    private static Lock barberlock = new ReentrantLock();
    private static Condition barberCondition = barberlock.newCondition();

    private static class Barber extends Thread {
        private boolean awake = true;

        public boolean isAwake() {
            return awake;
        }

        @Override
        public void run() {
            while(open) {
                Customer nextCustomer = null;

                synchronized (waitingRoom) {
                    if (!waitingRoom.isEmpty()) {
                        nextCustomer = waitingRoom.iterator().next();
                        waitingRoom.remove(nextCustomer);
                    }
                }

                if (nextCustomer != null) {
                    System.out.println("Barber: Cutting hair of customer " + nextCustomer.getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.err.println("Barber: Oh! I am fired!");
                        return;
                    }
                    System.out.println("Barber: Hair of customer " + nextCustomer.getName() + " is cut.");
                } else {
                    try {
                        System.err.println("Barber: No customers, going to sleep...");
                        synchronized (this) {
                            barberlock.lock();
                            awake = false;
                            barberCondition.await(2500, TimeUnit.SECONDS);
                            awake = true;
                            barberlock.unlock();
                        }
                    } catch (InterruptedException e) {
                        System.err.println("Barber: Oh! It is closing time!");
                        return;
                    }
                }
            }
        }
    }

    private static Barber barber = new Barber();

    private static class Customer extends Thread {
        public Customer(String name) {
            setName(name);
        }

        @Override
        public void run() {
            synchronized (waitingRoom) {
                if (waitingRoom.size() < WAITING_ROOM_SIZE) {
                    System.err.println("                                                  "
                            + getName() + ": Sitting in the waiting room.");
                    waitingRoom.add(this);
                    if (waitingRoom.size() == 1) {
                        barberlock.lock();
                            if (!barber.isAwake()) {
                                System.err.println("                                                  "
                                        + getName() + ": Waking up the barber.");
                                barberCondition.signal();
                            }
                        barberlock.unlock();
                    }
                } else {
                    System.err.println(getName() + ": Leaving.");
                }
            }
        }
    }

    public static void main(String args[]) {
        Random rand = new Random();
        
        barber.start();
        for (int i = 0; i < MAX_CUSTOMERS; ++i) {
            Customer customer = new Customer("Customer" + i);
            customer.start();
            try {
                Thread.sleep(rand.nextInt(2500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        open = false;
    }
}
