package dev.executorChallenge;

import java.util.Random;
import java.util.concurrent.Executors;

record Order(long orderId, String item, int qty) {
}

public class Main {
	public static void main(String[] args) {
		ShoeWarehouse shoeWarehouse = new ShoeWarehouse();
		var singleExecutorProducer = Executors.newSingleThreadExecutor();
		singleExecutorProducer.execute(() -> {
			for (int i = 0; i < 15; i++) {
				Order order=new Order(new Random().nextLong(500,999),
						ShoeWarehouse.PRODUCT_LIST[new Random().nextInt(0,5)],
						new Random().nextInt(1,5));
				shoeWarehouse.receiveOrder(order);
			}
		});
		singleExecutorProducer.shutdown();
		int customers=4;
		var multiConsumer=Executors.newFixedThreadPool(customers);
		for (int i = 0; i < 15; i++) {
		multiConsumer.execute(shoeWarehouse::fulfillOrder);
		}
		multiConsumer.shutdown();
	}
}
