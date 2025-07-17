package dev.StreamCollect;

import dev.JPA.MainQuery;

import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) {

		BinaryOperator<StringBuilder> combiner = (a, b) -> a;
		var st= Stream.of("Nick", "Java", "Streams")

				.collect(Collector.of(
						()->new StringBuilder(),
						(strBu,str)->strBu.append(str),
						(strBu1,strBu2)->strBu1.append(strBu2)
				));



		System.out.println(st);
	}
}
