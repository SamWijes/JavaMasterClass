package dev.managingFiles;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Main {
	public static void main(String[] args) {

//		File oldFile=new File("students.json");
//		File newFile=new File("students-activity.json");
//
//		if (oldFile.exists()){
//			oldFile.renameTo(newFile);
//			System.out.println("file renamed successfully");
//		}else System.out.println("File doesnt exist");

		Path oldPath = Path.of("students.json");
		Path newPath = Path.of("files2/students-activity.json");
//
//		try{
//			Files.move(newPath,oldPath);
//			System.out.println("Path renamed success");
//		}catch (IOException e){
//			e.printStackTrace();
//		}

//		try{
//			Files.createDirectories(newPath.subpath(0,newPath.getNameCount()-1));
//			Files.move(oldPath,newPath);
////			Files.move(newPath,oldPath);
//			System.out.println("Path renamed success");
//
//		}catch (IOException e){
//			e.printStackTrace();
//		}

		Path filesDir = Path.of("files2");
		Path resources = Path.of("resources");

		try {
			recursiveDelete(resources);
			recursiveCopy(filesDir, resources);
			System.out.println("Directory copied to " + resources);
		} catch (IOException e) {
			e.printStackTrace();
		}


//		try(BufferedReader reader=new BufferedReader(
//				new FileReader("files2/students-activity.json"));
//			PrintWriter writer= new PrintWriter("students-backup.json")){
//
//			reader.transferTo(writer);
//		}catch (IOException e){
//			throw new RuntimeException(e);
//		}

		String urlString = "https://api.census.gov/data/2019/pep/charagegroups?get=NAME,POP&for=state:*";

		URI uri = URI.create(urlString);
		try(var urlInputStream=uri.toURL().openStream()){
			urlInputStream.transferTo(System.out);
		}  catch (IOException e) {
			throw new RuntimeException(e);
		}

		Path jsonPath = Path.of("USPopulationByState.txt");
		try (var reader = new InputStreamReader(uri.toURL().openStream());
			 var writer = Files.newBufferedWriter(jsonPath)
		) {
			reader.transferTo(writer);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		try (var reader = new InputStreamReader(uri.toURL().openStream());
			 PrintWriter writer = new PrintWriter("USPopulationByState.csv")) {

			reader.transferTo(new Writer() {
				@Override
				public void write(char[] cbuf, int off, int len) throws IOException {
					String jsonString=new String(cbuf,off,len).trim();
					jsonString=jsonString.replace('[',' ');
					jsonString=jsonString.replaceAll("]","");
					writer.write(jsonString);
				}

				@Override
				public void flush() throws IOException {
					writer.flush();
				}

				@Override
				public void close() throws IOException {
					writer.close();
				}
			});
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	private static void recursiveCopy(Path source, Path target) throws IOException {

		Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
		if (Files.isDirectory(source)) {
			try (var children = Files.list(source)) {
				children.toList().forEach(path -> {
					try {
						Main.recursiveCopy(path, target.resolve(path.getFileName()));
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				});
			}
		}

	}

	private static void recursiveDelete(Path target) throws IOException {

		if (Files.isDirectory(target)) {
			try (var children = Files.list(target)) {
				children.toList().forEach(path -> {
					try {
						Main.recursiveDelete(path);
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				});
			}

		}
		Files.delete(target);
	}
}
