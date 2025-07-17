package dev.parallelStreamsMore;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

record Person(String firstName, String lastName, int age) {
	private final static String[] firsts =
			{"Able", "Bob", "Charlie", "Donna", "Eve", "Fred"};
	private final static String[] lasts =
			{"Norton", "OHara", "Petersen", "Quincy", "Richardson", "Smith"};

	private final static Random random = new Random();


	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName, age);
	}

	public Person() {
		this(firsts[random.nextInt(firsts.length)],
				lasts[random.nextInt(lasts.length)],
				random.nextInt(18, 100));
	}

	@Override
	public String toString() {
		return "%s, %s (%d)".formatted(lastName, firstName, age);
	}
}

public class Main {
	public static void main(String[] args) {

//		Stream.generate(Person::new).limit(10)
//				.parallel()
//				.sorted(Comparator.comparing(Person::lastName))
//				.forEach(System.out::println);

		var persons = Stream.generate(Person::new).limit(10)
				.sorted(Comparator.comparing(Person::lastName)).toArray();

		for (var person : persons) {
			System.out.println(person);
		}

		System.out.println("------------------");
		Arrays.stream(persons)
				.parallel()
//				.sorted(Comparator.comparing(Person::lastName))
				.forEachOrdered(System.out::println);
		System.out.println("------------------------------");
		int sum = IntStream.range(1, 101)
				.parallel()
				.reduce(0, Integer::sum);

		System.out.println("The Sum is " + sum);

		String humptyDumpty = """
				Humpty Dumpty sat on a wall.
				Humpty Dumpty had a great fall.
				All the king's horses and all the king's men
				couldn't put Humpty together again.
				""";

		System.out.println("-----------------------------");

		Scanner scanner = new Scanner(humptyDumpty);

		var words = scanner.tokens().toList();
		words.forEach(System.out::println);
		System.out.println("-----------------------------");

//		var backTogether=words
//				.parallelStream()// this stringjoiner isnt thread safe interleaving threads in parallel can cause issue
//				.reduce(new StringJoiner(" "),StringJoiner::add,StringJoiner::merge);


//		var backTogether = words
//				.parallelStream()// thi s stringjoiner isnt thread safe interleaving threads in parallel can cause issue
//				.collect(Collectors.joining(" "));


		var backTogether = words
				.parallelStream()// thi s stringjoiner isnt thread safe interleaving threads in parallel can cause issue
				.reduce("",(s1,s2)->s1.concat(s2).concat(" "));

		var lastNameCounts=Stream.generate(Person::new)
						.limit(10000)
								.collect(Collectors.groupingByConcurrent(     //<=>Collectors.groupingBy
										Person::lastName,
										Collectors.counting()));



		System.out.println(backTogether);

		lastNameCounts.entrySet().forEach(System.out::println);
		long total = 0;
		for (long count : lastNameCounts.values()) {
			total += count;
		}
		System.out.println("Total = " + total);

		//including lec 23 from here

		System.out.println(lastNameCounts.getClass().getName());

		//change a variable that is not a part of the pipeline
//		var lastCounts=new TreeMap<String,Long>();
//		Stream.generate(Person::new)
//				.limit(10000)
//				.parallel()
//				.forEach((person)->lastCounts.merge(person.lastName(), 1L,Long::sum));

		var lastCounts=Collections.synchronizedMap(new TreeMap<String ,Long>());
		Stream.generate(Person::new)
				.limit(10000)
				.parallel()
				.forEach((person)->lastCounts.merge(person.lastName(), 1L,Long::sum));

		System.out.println(lastCounts);

		total = 0;
		for (long count : lastCounts.values()) {
			total += count;
		}
		System.out.println("Total = " + total);

		System.out.println(lastCounts.getClass().getName());
	}
}
