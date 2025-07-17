package dev.readingCH;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {
		Path path = Path.of("pelton.txt");

//		try {
//			String fileText = new String(Files.readAllBytes(path));
//			var map=Files.readString(path).lines()
//					.map(l -> l.replaceAll("\\p{Punct}", ""))
//					.flatMap(s -> Arrays.stream(s.split(" ")))
//					.filter(l->l.length()>5)
//					.collect(Collectors.groupingBy(s -> s, TreeMap::new,Collectors.counting()));
//
//			var listMap=new ArrayList<>(map.entrySet());
//			listMap.sort(Map.Entry.<String,Long>comparingByValue().reversed());
//			for (int i = 0; i <10; i++) {
//				System.out.println(listMap.get(i));
//			}
//
////			System.out.println(fileText);
////			System.out.println("-----------------------------------------");
////			System.out.println(Files.readString(path));
//
//		} catch (IOException e) {
//			throw new RuntimeException();
//		}
		try(BufferedReader reader=new BufferedReader(new FileReader("pelton.txt"))) {
			reader.lines();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
