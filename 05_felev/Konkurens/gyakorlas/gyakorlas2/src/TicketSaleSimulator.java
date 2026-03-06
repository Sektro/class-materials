import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Egy koncertre negyfajta jegy kaphato: az A, B, C es D szektorokba.
 * Mindegyik jegyfajtabol 5 erheto el, osszesen tehat 20 jegy kaphato.
 * Mindegyik vasarlo eseten adott, melyik szektorba tartozo jegyet szeretne venni.
 */
public class TicketSaleSimulator {
    private static final int SLEEP_TIME_MIN = 100;
    private static final int SLEEP_TIME_MAX = 500;
    private static final int SHUTDOWN_TIME = 15000;

    private static final List<String> TICKET_TYPES = List.of("SECTOR A", "SECTOR B", "SECTOR C", "SECTOR D");

    private static final int TICKET_COUNT_PER_SELLER = 5;
    private static final int SELLER_COUNT = TICKET_TYPES.size();
    private static final int CUSTOMER_COUNT = SELLER_COUNT * TICKET_COUNT_PER_SELLER;

    private static Map<String, Integer> TICKET_INVENTORY = Collections.synchronizedMap(new TreeMap<>());
    private static Queue<String> TICKET_QUEUE = new LinkedBlockingQueue<String>(1);
    public static ExecutorService pool = Executors.newFixedThreadPool(128);

    public static void main(String[] args) throws InterruptedException {
        // Jegykeszlet feltoltese
        TICKET_TYPES.forEach(str -> TICKET_INVENTORY.put(str, TICKET_COUNT_PER_SELLER));

        for (String t : TICKET_TYPES) {
            pool.submit(() -> {
                try {
                    sellerAction(t);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        for (int i = 0; i < CUSTOMER_COUNT; ++i) {
            int index = i % SELLER_COUNT;
            String type = TICKET_TYPES.get(index);
            final int FinalI = i+1;
            pool.submit(() -> {
                try {
                    customerAction(FinalI,type);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        pool.shutdown();
        pool.awaitTermination(SHUTDOWN_TIME, TimeUnit.MILLISECONDS);
    }

    /**
     * Ez a jegyarus mukodesenek megvalositasa.
     */
    private synchronized static void sellerAction(String ticketType) throws InterruptedException {
        boolean out = false;
        while (!out) {
            System.out.printf("Started seller for %s%n", ticketType);

            if (TICKET_INVENTORY.get(ticketType) != 0) {
                TICKET_INVENTORY.replace(ticketType,TICKET_INVENTORY.get(ticketType)-1);
                TICKET_QUEUE.add(ticketType);
                System.out.println("New ticket available for " + ticketType);
            }

            Thread.sleep(105);

            if (TICKET_INVENTORY.get(ticketType) == 0) {
                System.out.printf("Tickets for %s sold out%n", ticketType);
                out = true;
            }
        }
    }

    /**
     * Ez a vasarlo mukodesenek megvalositasa.
     */
    private static void customerAction(int customerId, String ticketType) throws InterruptedException {
        boolean bought = false;
        while (!bought) {
            if (Objects.equals(TICKET_QUEUE.peek(), ticketType)) {
                TICKET_QUEUE.remove();
                System.out.printf("Customer %d got a ticket to %s%n", customerId, ticketType);
            }
            Thread.sleep(105);
        }
    }
}
