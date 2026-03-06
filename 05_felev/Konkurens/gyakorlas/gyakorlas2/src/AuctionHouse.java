import java.util.*;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class AuctionHouse {
    static class NFT {
        public final int artistIdx;
        public final int price;

        public NFT(int artistIdx, int price) {
            this.artistIdx = artistIdx;
            this.price = price;
        }
    }

    static class AuctionOffer {
        public int offeredSum;
        public String collectorName;

        public AuctionOffer(int offeredSum, String collectorName) {
            this.offeredSum = offeredSum;
            this.collectorName = collectorName;
        }
    }

    static int failCount = 0;

    static final int MAX_NFT_PRICE = 100;
    static final int MAX_NFT_IDX = 100_000;
    static final int MAX_COLLECTOR_OFFER = MAX_NFT_IDX / 100;

    private static final int COLLECTOR_MIN_SLEEP = 10;
    private static final int COLLECTOR_MAX_SLEEP = 20;
    private static final int MAX_AUCTION_OFFERS = 10;

    static final int ARTIST_COUNT = 10;
    static final int COLLECTOR_COUNT = 5;

    static final int INIT_ASSETS = MAX_NFT_IDX / 10 * MAX_NFT_PRICE;

    static int nftIdx = 0;
    static int remainingNftPrice = INIT_ASSETS;
    static NFT[] nfts = new NFT[MAX_NFT_IDX];

    static int totalCommission = 0;
    static int noAuctionAvailableCount = 0;
    static int soldItemCount = 0;

    // TODO for Task 2: data structure "auctionQueue"
    static LinkedBlockingQueue<AuctionOffer> auctionQueue;
    // TODO for Task 3: data structure "owners"


    public static void main(String[] args) throws InterruptedException {
        // Task 1
        List<Thread> artists = makeArtists();

        // Task 2
        Thread auctioneer = makeAuctioneer(artists);

        // Task 3
        List<Thread> collectors = makeCollectors(auctioneer);


        // TODO make sure that everybody starts working
        for (Thread t : artists) {
            t.start();
        }

        // TODO make sure that everybody finishes working

        runChecks();
    }

    // ------------------------------------------------------------------------
    // Task 1

    private static List<Thread> makeArtists() {
        // TODO create ARTIST_COUNT artists as threads, all of whom do the following, and return them as a list
        List<Thread> artists = new ArrayList<>(ARTIST_COUNT);

        // every 20 milliseconds, try to create an NFT in the following way
            // the artist chooses a price for the nft between 100 and 1000
            // if the nfts array is already fully filled, the artist is done
            // if the price is more than remainingNftPrice, the artist is done
            // the artist creates the NFT in the next nfts array slot
            // ... and deduces the price from remainingNftPrice
        for (int i = 0; i < ARTIST_COUNT; ++i) {
            final int FinalI = i;
            Thread xd = new Thread(new Runnable() {
                @Override
                public void run() {
                    Random rnd = new Random();
                    int price = rnd.nextInt(100,1001);
                    boolean canBeInserted = false;

                    for (int i = 0; i < nfts.length && !canBeInserted && remainingNftPrice > 0; ++i) {
                        if (nfts[i].price == 0) {
                            canBeInserted = true;
                            if (remainingNftPrice - price >= 0) {
                                NFT nft = new NFT(FinalI,price);
                                remainingNftPrice -= price;
                            }
                        }
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });
            artists.add(xd);
        }

        return artists;
    }

    // ------------------------------------------------------------------------
    // Task 2

    private static Thread makeAuctioneer(List<Thread> artists) {
        // TODO create and return the auctioneer thread that does the following

        // run an auction if 1. any artists are still working 2. run 100 auctions after all artists are finished
            // otherwise, the auctioneer is done
        // a single auction is done like this:
            // pick a random NFT from nfts (keep in mind that nfts can still be empty)
            // create the auctionQueue
            // wait for auction offers
                // if there were already MAX_AUCTION_OFFERS, the auction is done
                // if no offer is made within 1 millisecond, the auction is done
                // only for Task 3: if an offer is made and it has a better price than all previous ones, this is the currently winning offer
            // once the auction is done, add the commission to totalCommission
                // the commission is 10% of the price of the NFT (including the sum in the highest offer, if there was any)
                // only for Task 3: if there was an offer, increase soldItemCount and remember that the collector owns an NFT
            // now set auctionQueue to null and keep it like that for 3 milliseconds
        Thread auctioneer = new Thread(new Runnable() {
            @Override
            public void run() {
                int repeats = 100;
                boolean aliveExists = true;
                while (aliveExists && repeats > 0) {
                    ArrayList<Integer> auctionCandidates = new ArrayList<>();
                    for (int i = 0; i < nfts.length; ++i) {
                        if (nfts[i].price > 0) {
                            auctionCandidates.add(i);
                        }
                    }
                    if (!auctionCandidates.isEmpty()) {
                        Random rnd = new Random();
                        nftIdx = auctionCandidates.get(rnd.nextInt(0,auctionCandidates.size()));

                        auctionQueue = new LinkedBlockingQueue<>(MAX_AUCTION_OFFERS);
                        boolean doneWaiting = false;
                        int previousSize = 0;
                        while (!doneWaiting) {
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (auctionQueue.size() > previousSize && auctionQueue.size() < MAX_AUCTION_OFFERS) {
                                previousSize = auctionQueue.size();
                            }
                            else {
                                doneWaiting = true;
                            }
                        }

                        if (!auctionQueue.isEmpty()) {
                            ++soldItemCount;
                            AuctionOffer maxOffer;
                            try {
                                maxOffer = auctionQueue.take();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            for (int i = 0; i < auctionQueue.size(); ++i) {
                                try {
                                    AuctionOffer asd = auctionQueue.take();
                                    if (asd.offeredSum > maxOffer.offeredSum) {
                                        maxOffer = asd;
                                    }
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            totalCommission += (maxOffer.offeredSum + nfts[nftIdx].price) / 10;
                            nfts[nftIdx] = new NFT(-1,0);
                        }
                        else {
                            totalCommission += nfts[nftIdx].price / 10;
                            nfts[nftIdx] = new NFT(-1,0);
                        }

                        auctionQueue = null;
                        try {
                            Thread.sleep(3);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    aliveExists = false;
                    for (Thread a : artists) {
                        if (a.isAlive()) {
                            aliveExists = true;
                        }
                    }
                    if (!aliveExists) {
                        --repeats;
                    }
                }
            }
        });
        return auctioneer;
    }

    // ------------------------------------------------------------------------
    // Task 3

    private static List<Thread> makeCollectors(Thread auctioneer) {
        // TODO create collectors now, the collectors' names are simply Collector1, Collector2, ...

        // work until the auctioneer is done (it is not isAlive() anymore)
            // sleep for COLLECTOR_MIN_SLEEP..COLLECTOR_MAX_SLEEP milliseconds randomly between each step
        // if there is no auction available, just increase noAuctionAvailableCount
        // if there is an ongoing auction, and you haven't bid on it already, make an offer
            // choose your offer between 1..MAX_COLLECTOR_OFFER randomly
    }

    // ------------------------------------------------------------------------
    // Tester

    private static String isOK(boolean condition) {
        if (!condition)   ++failCount;
        return isOkTxt(condition);
    }

    private static String isOkTxt(boolean condition) {
        return condition ? "GOOD" : "BAD ";
    }

    private static void runChecks() {
        if (Thread.activeCount() == 1) {
            System.out.printf("%s Only the current thread is running%n", isOK(true));
        } else {
            System.out.printf("%s %d threads are active, there should be only one%n", isOK(Thread.activeCount() == 1), Thread.activeCount());
        }

        System.out.printf("%s nftIdx > 0%n", isOK(nftIdx > 0));

        int soldPrice = IntStream.range(0, nftIdx).map(idx-> nfts[idx].price).sum();
        System.out.printf("%s Money is not lost: %d + %d = %d%n", isOK(soldPrice + remainingNftPrice == INIT_ASSETS), soldPrice, remainingNftPrice, INIT_ASSETS);

        System.out.printf("%s [Only Task 2] Total commission not zero: %d > 0%n", isOK(totalCommission > 0), totalCommission, INIT_ASSETS);

        System.out.printf("%s [Only Task 3] Sold item count not zero: %d > 0%n", isOK(soldItemCount > 0), soldItemCount, INIT_ASSETS);
        System.out.printf("%s [Only Task 3] Some collectors have become owners of NFTs: %d > 0%n", isOK(owners.size() > 0), owners.size(), INIT_ASSETS);
        System.out.printf("%s [Only Task 3] Sometimes, collectors found no auction: %d > 0%n", isOK(noAuctionAvailableCount > 0), noAuctionAvailableCount, INIT_ASSETS);

        System.out.printf("%s Altogether %d condition%s failed%n", isOkTxt(failCount == 0), failCount, failCount == 1 ? "" : "s");

        // forcibly shutting down the program (don't YOU ever do this)
        System.exit(42);
    }

    // ------------------------------------------------------------------------
    // Utilities

    private static int getRandomBetween(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max+1);
    }

    private static void sleepForMsec(int msec) {
        try {
            Thread.sleep(msec);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
