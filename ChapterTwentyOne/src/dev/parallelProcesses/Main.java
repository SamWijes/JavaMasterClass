package dev.parallelProcesses;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.*;
import java.util.stream.Stream;

class RecursiveSumTask extends RecursiveTask<Long> {
	private final long[] numbers;
	private final int start;
	private final int end;
	private final int division;

	public RecursiveSumTask(long[] numbers, int start, int end, int division) {
		this.numbers = numbers;
		this.start = start;
		this.end = end;
		this.division = division;
	}

	@Override
	protected Long compute() {
		if ((end - start) <= numbers.length / division) {
			System.out.println(start + " : " + end);
			long sum = 0;
			for (int i = start; i < end; i++) {
				sum += numbers[i];
			}
			return sum;
		} else {
			int mid = (start + end) / 2;
			RecursiveSumTask leftTask = new RecursiveSumTask(numbers, start, mid, division);
			RecursiveSumTask rightTask = new RecursiveSumTask(numbers, mid, end, division);
			leftTask.fork();
			rightTask.fork();

			return leftTask.join()+rightTask.join();
		}
	}
}

public class Main {
	public static void main(String[] args) throws Exception {
		int numberLength = 100_000;
		long[] numbers = new Random().longs(numberLength, 1, numberLength).toArray();

		long sum = Arrays.stream(numbers).sum();

		System.out.println(sum);
//		ForkJoinPool threadPool = (ForkJoinPool) Executors.newWorkStealingPool(4);
		ForkJoinPool threadPool = ForkJoinPool.commonPool();
		List<Callable<Long>> tasks = new ArrayList<>();
		int taskNo = 10;
		int splitCount = numberLength / taskNo;

		for (int i = 0; i < taskNo; i++) {
			int start = i * splitCount;
			int end = start + splitCount;
			tasks.add(() -> {
				long tasksum = 0;
				for (int j = start; j < end; j++) {
					tasksum += numbers[j];
				}
				return tasksum;
			});
		}

		List<Future<Long>> futures = threadPool.invokeAll(tasks);
		System.out.println("CPUs= " + Runtime.getRuntime().availableProcessors());
		System.out.println("parallelism = " + threadPool.getParallelism());
		System.out.println("Pool Size = " + threadPool.getPoolSize());
		System.out.println("Steal Count = " + threadPool.getStealCount());
		long taskSum = 0;
		for (Future<Long> future : futures) {
			taskSum += future.get();
		}
		System.out.println("Thread Pool Sum =" + taskSum);
		RecursiveSumTask task=new RecursiveSumTask(numbers,0,numberLength,8);
		long forkJoinSum=threadPool.invoke(task);
		System.out.println("RecursivTask Sum is "+forkJoinSum);
		threadPool.shutdown();



		System.out.println(threadPool.getClass().getName());


	}
}
