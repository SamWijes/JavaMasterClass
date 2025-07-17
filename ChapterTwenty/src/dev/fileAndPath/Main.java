package dev.fileAndPath;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
	public static void main(String[] args) {
		useFile("testfile.txt");
		usePath("pathfile.txt");


	}

	private static void useFile(String filename) {
		File file = new File(filename);
		boolean fileExists = file.exists();
		System.out.printf("File %s %s%n", filename, fileExists ? "exists" : "doesnt exist");

		if (fileExists) {
			System.out.println("Deleting File");
			fileExists = !file.delete();
		}

		if (!fileExists) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("something went wrong here");
			}

			System.out.println("Create file " + filename);
			if (file.canWrite()) {
				System.out.println("Writing to file here");
			}
		}

	}

	private static void usePath(String filename) {
		Path path = Path.of(filename);
		boolean fileExists = Files.exists(path);
		System.out.printf("File %s %s%n", filename, fileExists ? "exists" : "doesnt exist");

		if (fileExists) {
			System.out.println("Deleting File");
			try {
				Files.delete(path);
				fileExists = false;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (!fileExists) {
			try {
				Files.createFile(path);
				System.out.println("Create file " + filename);
				if (Files.isWritable(path)) {
//					System.out.println("Writing to file here");
					Files.writeString(path, """
							Here is some data
							for myfile just to prove
							using file class path is better						
							""");
				}
				System.out.println("Reading file--->>");
				Files.readAllLines(path).forEach(System.out::println);
			} catch (IOException e) {
				System.out.println("something went wrong here");
			}
		}

	}
}
