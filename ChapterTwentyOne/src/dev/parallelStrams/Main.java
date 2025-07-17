package dev.parallelStrams;

import java.util.Arrays;
import java.util.Random;

public class Main {
	public static void main(String[] args) {
		int numbersLength=100_000_000;
		long[] numbers=new Random().longs(numbersLength,1,numbersLength).toArray();
		long start=System.nanoTime();


		long delta=0;
		int iterations=25;

		for (int i = 0; i < iterations; i++) {
			double averageSerial = Arrays.stream(numbers).average().orElseThrow();
			long elapsedSerial = System.nanoTime() - start;
//			System.out.printf("AveS = %.2f , elapsed = %d nanos or %.2f ms%n", averageSerial, elapsedSerial, elapsedSerial / 1000000.0);


			start = System.nanoTime();
			double averageParallel = Arrays.stream(numbers).parallel().average().orElseThrow();
			long elapsedParallel = System.nanoTime() - start;
//			System.out.printf("AveP = %.2f , elapsed = %d nanos or %.2f ms%n", averageParallel, elapsedParallel, elapsedParallel / 1000000.0);
			delta+=(elapsedSerial-elapsedParallel);

		}
		System.out.printf("Parallel is [%d] nanos or [%.2f] ms faster on average %n",
				delta / iterations, delta / 1000000.0 / iterations);


//		long sum=0;
//		for (int i = 0; i < numbersLength; i++) {
//			sum+=numbers[i];
//		}
//		System.out.println(sum +" time lapsed "+((System.nanoTime()-start)/1000000.0));
//
//		start=System.nanoTime();
//		System.out.println(Arrays.stream(numbers).sum()+" time lapsed "+((System.nanoTime()-start)/1000000.0));


	}
}
