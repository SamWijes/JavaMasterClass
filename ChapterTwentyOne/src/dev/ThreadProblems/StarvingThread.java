package dev.ThreadProblems;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class StarvingThread {

	private static final Lock lock = new ReentrantLock();

	public static void main(String[] args) {

		Callable<Boolean> thread = () -> {
			String threadName = Thread.currentThread().getName();//pool-1-thread-1
			var threadNo = Integer.parseInt(threadName.replaceAll(".*-[1-9]-.*-", ""));

			boolean keepGoing = true;
			while (keepGoing) {


				lock.lock();
				try {
					System.out.printf("%d acquired the lock .%n", threadNo);
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					System.out.printf("Shutting down %d%n", threadNo);
					Thread.currentThread().interrupt();
					return false;
				} finally {
					lock.unlock();
				}
			}
			return true;
		};


		var executor = Executors.newFixedThreadPool(3);

		try {
			var futures = executor.invokeAll(List.of(thread, thread, thread),
					10,
					TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		executor.shutdownNow();

	}
}
