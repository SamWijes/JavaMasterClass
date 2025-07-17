package dev.synchChallenge12;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

record Order(int id, String shoeType, int quantity) {
}

public class ShoeWarehouse {
	public static List<String> productList = List.of("Sneaker", "High Heels", "Saddle Boots", "Slippers");

	private List<Order> orderList;
	private static Random random = new Random();

	private static int initialId = 1;

	public static int getInitialId() {
		return initialId;
	}

	public static void setInitialId(int initialId) {
		ShoeWarehouse.initialId = initialId;
	}

	//	private List<Order> orderList=new ArrayList<>();  //samething as constructor
	public ShoeWarehouse() {
		this.orderList = new ArrayList<>();
	}

	public synchronized void receiveOrder(Order item) {

//		System.out.println("Now receiving orders");
		while (orderList.size() > 20) {
			try {
				wait();  //stop adding if the queue is full
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		orderList.add(item);
		System.out.println("adding " + item);
		notifyAll();//after adding items release lock
		//this is needed to resume fullfill operations consumer threads

	}
	//orderList.forEach(System.out::println);

	public synchronized void fulfillOrder() {
		//System.out.println("current Available orders="+ orderList.size());
		while (orderList.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

		}
		while (!orderList.isEmpty()) {
			System.out.println(Thread.currentThread().getName() + " fullfilling--> " + orderList.getFirst());
			orderList.removeFirst();
			try {
				wait();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		notifyAll();

	}

}
