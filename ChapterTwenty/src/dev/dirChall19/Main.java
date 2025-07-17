package dev.dirChall19;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Main {
	public static void main(String[] args) {
		//root/public/assets/icons
		//process to generate index.txt

		Path path = Path.of("public/assets/icons");

		try {
			if (!Files.isDirectory(path)) {
				Files.createDirectories(path);
			}
//			for (int i = 0; i < path.getNameCount() ; i++) {
//				System.out.println(path.getName(i));
//			}
//			for (int i = 0; i < path.getNameCount(); i++) {
//				String file=path.getName(i)+"/index.txt";
//				if (Files.exists(Path.of(file))) {
//
//				}
//			}
			recursiveIndex("public/assets/icons");

//			System.out.println(path.subpath(0,path.getNameCount()-1));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void recursiveIndex(String path) throws IOException {
		Path initPath = Path.of(path);
		Path indexPath = Paths.get(path, "index.txt");
		if (!Files.exists(indexPath)) {
				Files.createFile(indexPath);

			}

		try (PrintWriter writer = new PrintWriter(indexPath.toString());
			BufferedReader reader=Files.newBufferedReader(indexPath)) {
			String firstLine=reader.readLine();
			if (firstLine==null){writer.println("Directory,Date Modified");}
			var fileList = Files.list(initPath).map(Path::toAbsolutePath).toList();
			for (Path path1 : fileList) {
				Instant timeFile=Files.getLastModifiedTime(path1).toInstant();
				LocalDateTime time= LocalDateTime.ofInstant(timeFile, ZoneId.systemDefault());
				writer.println(path1+","+"%1$tF %1$tT".formatted(time));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (initPath.getNameCount() > 1) {
			Path nextDir = initPath.subpath(0, initPath.getNameCount() - 1);
			recursiveIndex(nextDir.toString());
		}


	}
}
