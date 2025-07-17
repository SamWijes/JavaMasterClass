package dev.randomAchallenge;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BuildStudentData {

	public static void build(String datFileName,boolean seperateIndex) {
		Path studentJson = Path.of("students.json");
		String dateFile = datFileName + ".dat";
		Map<Long, Long> indexedIds = new LinkedHashMap<>();

		try {
			Files.deleteIfExists(Path.of(dateFile));
			String data = Files.readString(studentJson);
			data = data.replaceAll("^(\\[)", "").replaceFirst("]$", "");
			var records = data.split(System.lineSeparator());
			System.out.println("# of records = " + records.length);
			//Starting point based on calculation 16* length of each line
			long startingPos =seperateIndex?0: 4 + 16L * records.length;
			//since student start with "studentId":1 get the pattern
			Pattern idPattern = Pattern.compile("\"studentId\":([0-9]+)");

			try (RandomAccessFile ra = new RandomAccessFile(dateFile, "rw")) {
				ra.seek(startingPos);
				for (String record : records) {
					Matcher m = idPattern.matcher(record);
					if (m.find()) {
						long id = Long.parseLong(m.group(1));
						indexedIds.put(id, ra.getFilePointer());
						ra.writeUTF(record);
					}
				}
				writeIndex(seperateIndex? new RandomAccessFile(datFileName+".idx","rw"):
						ra, indexedIds);

			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}


	}

	private static void writeIndex(RandomAccessFile ra, Map<Long, Long> indexMap) {
		try {
			ra.seek(0);
			ra.writeInt(indexMap.size());
			for (var studentIdx : indexMap.entrySet()) {
				ra.writeLong(studentIdx.getKey());
				ra.writeLong(studentIdx.getValue());
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

}
