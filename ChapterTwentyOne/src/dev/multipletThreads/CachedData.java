package dev.multipletThreads;

public class CachedData {
	private  boolean flag=false;

	private void toggleFlag(){
		flag=!flag;
	}

	public boolean isReady(){
		return flag;
	}

	public static void main(String[] args) {
		CachedData example=new CachedData();

		Thread writerThread=new Thread(()->{
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			example.toggleFlag();//true
			System.out.println("A Flag is set to "+example.isReady());
		});

		Thread readerThread=new Thread(()->{
			while (!example.isReady()){

			}
			System.out.println("B Flag is "+example.isReady());
		});

		writerThread.start();
		readerThread.start();



	}

}
