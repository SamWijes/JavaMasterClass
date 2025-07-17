package dev.randomAccess;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

	private static Map<Long, Long> indexedIds = new LinkedHashMap<>();

	private static int recordsInFile = 0;

	static {
		try (RandomAccessFile rb= new RandomAccessFile("student.idx","r");){
			loadIndex(rb,0);
		}catch (IOException e){
			throw new RuntimeException(e);
		}
	}
	public static void main(String[] args) {

		BuildStudentData.build("student",true);  //add boolean




		try (RandomAccessFile ra = new RandomAccessFile("student.dat", "r")) { //change studentData.dat
//			loadIndex(ra, 0);   //loaded in static initializer
			Scanner scanner=new Scanner(System.in);
			System.out.println("Enter student id or 0 to quit");
			while (scanner.hasNext()){
				long studentId=Long.parseLong(scanner.nextLine());
				while (studentId>recordsInFile){
					System.out.println("Enter another student id or 0 to quit");
					studentId=Long.parseLong(scanner.nextLine());
				}

				if (studentId<1){
					break;
				}
				ra.seek(indexedIds.get(studentId));
				String targetRecord=ra.readUTF();
				System.out.println(targetRecord);
				System.out.println("Enter another student id or 0 to quit");
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	private static void loadIndex(RandomAccessFile ra, int indexPosition) {
		try {
			ra.seek(indexPosition);
			recordsInFile = ra.readInt();
			System.out.println(recordsInFile);
			for (int i = 0; i < recordsInFile; i++) {
				indexedIds.put(ra.readLong(), ra.readLong());
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}


	}
}
