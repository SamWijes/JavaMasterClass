package dev.executors;

import java.util.List;
import java.util.concurrent.*;

class ColourThreadFactory implements ThreadFactory {

	private String threadName;
	private int colourValue = 1;

	public ColourThreadFactory() {
	}

	public ColourThreadFactory(ThreadColour colour) {
		this.threadName = colour.name();
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r);
		String name = threadName;
		if (name == null) {
			name = ThreadColour.values()[colourValue].name();
		}
		if (++colourValue > (ThreadColour.values().length - 1)) {
			colourValue = 1;
		}
		thread.setName(name);
		return thread;
	}
}

public class Main {
	public static void main(String[] args) {
		var multiExecutor = Executors.newCachedThreadPool();
		List<Callable<Integer>> taskList = List.of(
				() -> Main.sum(1, 10, 1, "red"),
				() -> Main.sum(10, 100, 10, "blue"),
				() -> Main.sum(2, 20, 2, "green")
				);
		try {
			var results = multiExecutor.invokeAny(taskList);
//			for (var result : results) {
				System.out.println(results);
//			}
		}catch (InterruptedException|ExecutionException e){
			throw new RuntimeException();
		} finally {
			multiExecutor.shutdown();
		}
	}

	public static void cachedmain(String[] args) {

		var multiExecutor = Executors.newCachedThreadPool();
		try {
			var redValue = multiExecutor.submit(() -> Main.sum(1, 10, 1, "red"));
			var blueValue = multiExecutor.submit(() -> Main.sum(10, 100, 10, "blue"));
			var greenValue = multiExecutor.submit(() -> Main.sum(2, 20, 2, "green"));

//			multiExecutor.execute(()->Main.sum(1,10,1,"yellow"));
//			multiExecutor.execute(()->Main.sum(10,100,10,"cyan"));
//			multiExecutor.execute(()->Main.sum(2,20,2,"purple"));
//

//			try {
//				TimeUnit.SECONDS.sleep(1);
//			} catch (InterruptedException e) {
//				throw new RuntimeException(e);
//			}
//			System.out.println("Starting new Threads");
//			for (var colour : new String[]{"red", "blue", "cyan", "purple", "green"}) {
//				multiExecutor.execute(()->Main.sum(1,10,2,colour));
//			}
			try {
				System.out.println(redValue.get(500, TimeUnit.MILLISECONDS));
				System.out.println(blueValue.get(500, TimeUnit.MILLISECONDS));
				System.out.println(greenValue.get(500, TimeUnit.MILLISECONDS));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}


		} finally {
			multiExecutor.shutdown();
		}

	}

	public static void fixedmain(String[] args) {
		int count = 3;
		var multiThread = Executors.newFixedThreadPool(count,
				new ColourThreadFactory());

		for (int i = 0; i < 6; i++) {
			multiThread.execute(Main::countDown);
		}
//		multiThread.execute(Main::countDown);
		multiThread.shutdown();

	}

	public static void singlemain(String[] args) {
		var blueExecutor = Executors.newSingleThreadExecutor(
				new ColourThreadFactory(ThreadColour.ANSI_BLUE));
		blueExecutor.execute(Main::countDown);
		blueExecutor.shutdown();


		boolean isDone = false;
		try {
			isDone = blueExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		if (isDone) {
			System.out.println("Blue finished yellow starting");
			var yellowExecutor = Executors.newSingleThreadExecutor(
					new ColourThreadFactory(ThreadColour.ANSI_YELLOW));
			yellowExecutor.execute(Main::countDown);
			yellowExecutor.shutdown();
			try {
				isDone = yellowExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			if (isDone) {
				System.out.println("yellow finished red starting");
				var redExecutor = Executors.newSingleThreadExecutor(
						new ColourThreadFactory(ThreadColour.ANSI_RED));
				redExecutor.execute(Main::countDown);
				redExecutor.shutdown();
			}
		}
	}

	public static void notmain(String[] args) {
		Thread blue = new Thread(Main::countDown, ThreadColour.ANSI_BLUE.name());
		Thread yellow = new Thread(Main::countDown, ThreadColour.ANSI_YELLOW.name());
		Thread red = new Thread(Main::countDown, ThreadColour.ANSI_RED.name());

		blue.start();
//		try {
//			blue.join();
//		} catch (InterruptedException e) {
//			throw new RuntimeException(e);
//		}
		yellow.start();
//		try {
//			yellow.join();
//		} catch (InterruptedException e) {
//			throw new RuntimeException(e);
//		}
		red.start();

//
//		try {
//			red.join();
//		} catch (InterruptedException e) {
//			throw new RuntimeException(e);
//		}

		System.out.println("check exit");

	}

	private static void countDown() {
		String threadName = Thread.currentThread().getName();
		var threadColour = ThreadColour.ANSI_RESET;
		try {
			threadColour = ThreadColour.valueOf(threadName.toUpperCase());
		} catch (IllegalArgumentException ignore) {
			//user pass a bad colour
		}
		String colour = threadColour.colour();
		for (int i = 20; i >= 0; i--) {
			System.out.println(colour + " " + threadName.replace("ANSI_", "") + " " + i);
		}
	}

	private static int sum(int start, int end, int delta, String colourString) {
		var threadColour = ThreadColour.ANSI_RESET;
		try {
			threadColour = ThreadColour.valueOf("ANSI_" + colourString.toUpperCase());
		} catch (IllegalArgumentException e) {
			//bad colour
		}
		int sum = 0;
		for (int i = start; i <= end; i += delta) {
			sum += i;
		}
		String colour = threadColour.colour();
		System.out.println(colour + Thread.currentThread().getName() + ", " + colourString + " " + sum);
		return sum;
	}
}
