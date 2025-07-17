package dev.synchChallenge12;

import java.util.Random;

public class Main {
	public static void main(String[] args) {
		ShoeWarehouse warehouse=new ShoeWarehouse();

		Thread producerThread=new Thread(()->{
			for (int i = 0; i < 10; i++) {
				//adding 10 items per batch
				int lastID=ShoeWarehouse.getInitialId();
				ShoeWarehouse.setInitialId(lastID+1);
				Order order=new Order(lastID,ShoeWarehouse.productList.get(new Random().nextInt(0,3)),
						new Random().nextInt(1,5));
				warehouse.receiveOrder(order);
			}
		});

		Thread consumer1=new Thread(warehouse::fulfillOrder);
		Thread consumer2=new Thread(warehouse::fulfillOrder);
		producerThread.start();
		consumer1.start();
		consumer2.start();

	}
}
