package dev.writeChallenge16;

import dev.writingFiles.Course;
import dev.writingFiles.Student;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) {
		String header = """
				Student Id,Country Code,Enrolled Year,Age,Gender,\
				Experienced,Course Code,Engagement Month,Engagement Year,\
				Engagement Type""";


		dev.writingFiles.Course jmc = new dev.writingFiles.Course("JMC", "Java Masterclass");
		dev.writingFiles.Course pymc = new Course("PYC", "Python Masterclass");
		List<dev.writingFiles.Student> students = Stream.generate(() -> Student.getRandomStudent(jmc, pymc))
				.limit(2)
				.toList();

		Path path = Path.of("challenge.json");
		try (PrintWriter writer = new PrintWriter("challenge.json")) {
			writer.println("{");
			for (Student student : students) {

				writer.printf("\"studentID\":%d,%n",student.getStudentId());
				writer.printf("\t\"countryCode\":\"%s\",%n",student.getCountry());
				writer.printf("\t\"enrolledMonth\":%d,%n",student.getEnrollmentMonth());
				writer.printf("\t\"enrolledYear\":%d,%n",student.getEnrollmentYear());
				writer.printf("\t\"ageAtEnrollment\":%d,%n",student.getEnrollmentAge());
				writer.printf("\t\"gender\":\"%s\",%n",student.getGender());
				writer.printf("\t\"previousProgrammingExperience\":%s%n",student.hasExperience());
				writer.println();
			}
			writer.println("}");



		} catch (IOException e) {
			throw new RuntimeException(e);
		}


	}

//	private static void dataMap(Student student){
//		Map<>
//	}
}
