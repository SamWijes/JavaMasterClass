package dev.fileExceptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
	public static void main(String[] args) {

		System.out.println("Current working dit (CWD)= "+
				new File("").getAbsolutePath());
		String filename="file/testing.csv";

		File file=new File(".",filename);
		System.out.println(file.getAbsolutePath());


		if (!file.exists()){
			System.out.println("Cant run no file");

		}else System.out.println("good to go");

		for (File listRoot : File.listRoots()) {
			System.out.println(listRoot);
		}

		Path path=Paths.get("file/testing.csv");

		if (!Files.exists(path)){
			System.out.println("2.Cant run no file");

		}else System.out.println("2.good to go");


	}

	private static void testFile(String filename){
		Path path= Paths.get(filename);
		FileReader reader=null;
		try {
//			List<String> lines= Files.readAllLines(path);
			reader=new FileReader(filename);
		} catch (IOException e) {
			if (reader!=null){
				try {
					reader.close();
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			}
			throw new RuntimeException(e);
		} finally {
			System.out.println("Maybe I'd log something either way...");
		}

		System.out.println("file exists able to use resource");
	}

	private static void testFile2(String filename){
		try (FileReader reader = new FileReader(filename)) {
		} catch (FileNotFoundException e) {
			System.out.println("File '" + filename + "' does not exist");
			throw new RuntimeException(e);
		} catch (NullPointerException | IllegalArgumentException  badData) {
			System.out.println("User has added bad data " + badData.getMessage());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}catch (Exception e){
			System.out.println("something unexpected happended");
		} finally {
			System.out.println("Maybe I'd log something either way...");
		}
		System.out.println("file exists able to use resource");
	}
}
