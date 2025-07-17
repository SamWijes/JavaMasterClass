package dev.pathListing;

import javax.imageio.IIOException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Instant;

public class Main {
	public static void main(String[] args) {
		Path path = Path.of("this/new/dir/files/testing.txt");
		printPathInfo(path);
		logStatement(path);

		extraInfo(path);
	}

	private static void printPathInfo(Path path) {
		System.out.println("Path: " + path);
		System.out.println("filename: " + path.getFileName());
		System.out.println("Parent: " + path.getParent());
		Path absPath = path.toAbsolutePath();
		System.out.println("abs Path" + absPath);
		System.out.println("abs Path root" + absPath.getRoot());
		System.out.println("Root = " + path.getRoot());
		System.out.println("isAbsolute = " + path.isAbsolute());
		System.out.println(absPath.getRoot());
//		int i = 1;
//		var it=path.toAbsolutePath().iterator();
//		while (it.hasNext()){
//			System.out.println( ".".repeat(i++)+it.next());
//		}


		int pathParts=absPath.getNameCount();//depth of directory tree
		for (int i = 0; i < pathParts; i++) {
			System.out.println(".".repeat(i+1)+absPath.getName(i));
		}
		System.out.println("-------------------------");

	}

	private static void logStatement(Path path) {
		try {
			Path parent=path.getParent();
			if (!Files.exists(parent)){
				Files.createDirectories(parent);
				Files.writeString(path, Instant.now()+": hello file\n",
						StandardOpenOption.CREATE,StandardOpenOption.APPEND);
			}
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	private static void extraInfo(Path path) {

		try {
			var attrib = Files.readAttributes(path, "*");
			attrib.entrySet().forEach(System.out::println);
			System.out.println(Files.probeContentType(path));
		} catch (IOException e) {
			System.out.println("problem getting attributes");
		}
	}
}
