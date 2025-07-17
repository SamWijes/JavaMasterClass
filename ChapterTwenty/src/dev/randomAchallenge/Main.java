package dev.randomAchallenge;

import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.util.*;

public class Main {

	private static Map<Integer, Long> indexedIds = new LinkedHashMap<>();

	private static int recordsInFile = 0;

//	static {
//		try (RandomAccessFile rb= new RandomAccessFile("student.idx","r");){
//			loadIndex(rb,0);
//		}catch (IOException e){
//			throw new RuntimeException(e);
//		}
//	}
	public static void main(String[] args) {

//		BuildStudentData.build("student",true);  //add boolean




		try (RandomAccessFile ra = new RandomAccessFile("employees.dat", "r")) { //change studentData.dat
			loadIndex(ra, 0);   //loaded in static initializer
			Scanner scanner=new Scanner(System.in);
			indexedIds.keySet().stream().sorted().toList().forEach(s-> System.out.print(s+ ","));
			System.out.println();

			System.out.println("Enter Employee id or 0 to quit");
			while (scanner.hasNext()){
				int employeeId= Integer.parseInt (scanner.nextLine());
				if (employeeId<1){
					break;
				}
				ra.seek(indexedIds.get(employeeId));
				int id=ra.readInt();
				double salary=ra.readDouble();
				String firstName=ra.readUTF();
				String lastName=ra.readUTF();
				System.out.printf("EmployeeID-%d Salary-%.2f FirstName-%s Lastname-%s%n",id,salary,firstName,lastName);
				System.out.println("Enter another Employee id or 0 to quit");
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
				indexedIds.put(ra.readInt(), ra.readLong());
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}


	}
}
