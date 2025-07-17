package dev.fileListings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) {
		Path path = Path.of("");
		System.out.println("cwd: " + path.toAbsolutePath());

		try (Stream<Path> paths = Files.list(path)) {
			paths
					.map(Main::listDir)
					.forEach(System.out::println);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		System.out.println("-------------------------");
		try (Stream<Path> paths = Files.walk(path, 2)) {
			paths
					.filter(Files::isRegularFile)
					.map(Main::listDir)
					.forEach(System.out::println);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		System.out.println("-------------------------");
		try (Stream<Path> paths = Files.find(path, Integer.MAX_VALUE,
				(p, attr) -> attr.isRegularFile() && attr.size() > 300)) {

			paths
					.map(Main::listDir)
					.forEach(System.out::println);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		path = path.resolve(".idea");
		System.out.println("===========Directory Stream==========");

		try (var dirs = Files.newDirectoryStream(path, "*.xml")) {
			dirs.forEach(d -> System.out.println(Main.listDir(d)));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		System.out.println("===========Directory Stream==========");
		try (var dirs = Files.newDirectoryStream(path,
				p -> p.getFileName().toString().endsWith(".xml")
						&& Files.isRegularFile(p) && Files.size(p) > 1000
		)) {
			dirs.forEach(d -> System.out.println(Main.listDir(d)));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	private static String listDir(Path path) {
		try {
			boolean isDir = Files.isDirectory(path);
			FileTime dateField = Files.getLastModifiedTime(path);
			LocalDateTime modTime = LocalDateTime.ofInstant(dateField.toInstant(), ZoneId.systemDefault());
			return "%tD %tT %-5s %12s %s".formatted(modTime, modTime, (isDir ? "<DIR>" : ""), (isDir ? "" : Files.size(path)), path);
		} catch (IOException e) {
			System.out.println("something went wrong " + path);
			return path.toString();
		}

	}
}
