package dev.runningThread;

public class Main {
	public static void main(String[] args) {
		System.out.println("Main thread is running");
		try {
			Thread.sleep(1000);
			System.out.println("Main threas is paused for 1s");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

//		Thread thread=new Thread(()->{
//
//		})

		System.out.println("Main thread will continue here");

	}
}
