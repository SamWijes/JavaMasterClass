package dev.schedulingTasks;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class Main {
	public static void main(String[] args) {
		var dtf= DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);

		Callable<ZonedDateTime> waitThenDoIt=()->{
			ZonedDateTime zdt=null;
			try {
				TimeUnit.SECONDS.sleep(2);
				zdt=ZonedDateTime.now();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			return zdt;
		};

		var threadFixed= Executors.newFixedThreadPool(2);

		List<Callable<ZonedDateTime>> list = Collections.nCopies(4, waitThenDoIt);
		try {
			System.out.println("-->"+ZonedDateTime.now().format(dtf));

			List<Future<ZonedDateTime>> futuresList = threadFixed.invokeAll(list);
			for (Future<ZonedDateTime> result : futuresList) {
				try {
					System.out.println(result.get(1,TimeUnit.SECONDS).format(dtf));
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			threadFixed.shutdown();
		}

		Runnable dateTask=()->{
			try {
				TimeUnit.SECONDS.sleep(3);
				System.out.println("a "+ZonedDateTime.now().format(dtf));
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		};


		System.out.println("-->"+ZonedDateTime.now().format(dtf));
		ScheduledExecutorService executor=Executors.newScheduledThreadPool(2);
//		for (int i = 0; i <4 ; i++) {
//			executor.schedule(() -> System.out.println(ZonedDateTime.now().format(dtf)), 2*(i+1), TimeUnit.SECONDS);
//		}
//		executor.shutdown();

		var scheduleTask= executor.scheduleWithFixedDelay(dateTask,
				2,2, TimeUnit.SECONDS);

		var scheduleTask2= executor.scheduleAtFixedRate(()-> System.out.println("b "+ZonedDateTime.now().format(dtf)),
				2,2, TimeUnit.SECONDS);
		long time=System.currentTimeMillis();
		while (!scheduleTask.isDone()) {
			try {
				TimeUnit.SECONDS.sleep(2);
				if ((System.currentTimeMillis() - time) / 1000 > 10) {
					scheduleTask.cancel(true);
				}
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		executor.shutdown();
	}
}
