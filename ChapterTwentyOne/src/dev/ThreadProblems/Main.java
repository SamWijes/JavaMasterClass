package dev.ThreadProblems;

import java.io.File;

public class Main {
	public static void main(String[] args) {
		File resourceA = new File("inputData.csv");
		File resourceB = new File("outputData.json");

		Thread threadA = new Thread(() -> {
			String threadName = Thread.currentThread().getName();
			System.out.println(threadName + " attempting lock in resourceA");
			synchronized (resourceA) {
				System.out.println(threadName + " has acquired lock on resourceA(csv)");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(threadName + "next attempting lock on resourceB(json),still locked on resA(csv)");
				synchronized (resourceB) {
					System.out.println(threadName + " has acquired lock on resourceB(json)");
				}
				System.out.println(threadName + " has released lock on resourceB(json)");
			}
			System.out.println(threadName + " has released lock on resourceA(csv)");
		}, "THREAD-A");


		Thread threadB = new Thread(() -> {
//			String threadName = Thread.currentThread().getName();
//			System.out.println(threadName + " attempting lock in resourceB");
//			synchronized (resourceB) {
//				System.out.println(threadName + " has acquired lock on resourceB(json)");
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				System.out.println(threadName + " next attempting lock on resourceA(csv),still locked on resB(json)");
//				synchronized (resourceA) {
//					System.out.println(threadName + " has acquired lock on resourceA(csv)");
//				}
//				System.out.println(threadName + " has released lock on resourceA(csv)");
//
//			}
//			System.out.println(threadName + " has released lock on resourceB(json)");

			String threadName = Thread.currentThread().getName();
			System.out.println(threadName + " attempting lock in resourceA");
			synchronized (resourceA) {
				System.out.println(threadName + " has acquired lock on resourceA(csv)");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(threadName + "next attempting lock on resourceB(json),still locked on resA(csv)");
				synchronized (resourceB) {
					System.out.println(threadName + " has acquired lock on resourceB(json)");
				}
				System.out.println(threadName + " has released lock on resourceB(json)");
			}
			System.out.println(threadName + " has released lock on resourceA(csv)");


		}, "THREAD-B ");

		threadA.start();
		threadB.start();

		try {
			threadA.join();
			threadB.join();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}


	}
}
