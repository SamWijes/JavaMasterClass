package dev.readingWithNIO2;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args) {
		System.out.println(System.getProperty("file.encoding"));
		System.out.println(Charset.defaultCharset());

		Path path= Path.of("fixedWidth.txt");

		try{
			String fileText=new String(Files.readAllBytes(path));
			System.out.println(fileText);
			System.out.println("-----------------------------------------");
			System.out.println(Files.readString(path));

		}catch (IOException e){
			throw new RuntimeException();
		}

	}
}
