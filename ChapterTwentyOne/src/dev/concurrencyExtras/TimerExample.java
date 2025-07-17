package dev.concurrencyExtras;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TimerExample {
	public static void main(String[] args) {
//		Timer timer=new Timer();

		TimerTask task=new TimerTask() {
			@Override
			public void run() {
				String threadName=Thread.currentThread().getName();
				DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

				System.out.println(threadName+" Time task executed at: "+LocalDateTime.now().format(formatter));
			}
		};

//		timer.scheduleAtFixedRate(task,0,1000);

		var executor= Executors.newSingleThreadScheduledExecutor();
		executor.scheduleAtFixedRate(task,0,2, TimeUnit.SECONDS);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		executor.shutdown();
//		timer.cancel();
	}
}
