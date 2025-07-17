package dev.threadCH05;

import java.util.List;

public class OddThread extends Thread {

	@Override
	public void run() {

		for (var num : List.of(1,3,5,7,11)) {
			System.out.print(num + " ");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				break;
//				e.printStackTrace();
			}
		}
	}
}
