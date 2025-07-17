package dev.threadCH05;

import java.util.List;

public class Main {
	public static void main(String[] args) {


		Runnable myRun = () -> {
			for (var num : List.of(2, 4, 6, 8, 10)) {
				System.out.print(num + " ");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					System.out.println("\n"+Thread.currentThread().getName() +" is interrupted");
					Thread.currentThread().interrupt();
					break;
//					e.printStackTrace();
				}
			}
		};

		Thread threadEven = new Thread(myRun);
		OddThread oddThread = new OddThread();
		threadEven.start();
		oddThread.start();

		long lapse=System.currentTimeMillis();

		if (lapse>2000){
			threadEven.interrupt();
		}


	}

	public static void isEven(boolean iseven, int count) {
		int i = 1;
		while (count > 0) {
			if (iseven) {
				if (i % 2 == 0) {
					System.out.print(i + " ");
					count--;
				}


			} else if (i % 2 != 0) {
				System.out.print(i + " ");
				count--;
			}
			i++;

		}
	}
}
