import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPools1 {
	public static void main(String[] args) throws Exception {
		int POOL_SIZE = 2;
		int THREAD_COUNT = 10;
		int STEP_COUNT = 100;

		var pool = Executors.newFixedThreadPool(POOL_SIZE);

		for (int i = 0; i < THREAD_COUNT; i++) {
			var threadIdx = i;
			pool.submit(() -> {
				for (int j = 0; j < STEP_COUNT; j++) {
					System.out.printf("Thread %d step %d%n", threadIdx, j);
				}
			});
		}

		pool.shutdown();
		pool.awaitTermination(1, TimeUnit.MINUTES);

		System.out.println("Done");
	}
}
