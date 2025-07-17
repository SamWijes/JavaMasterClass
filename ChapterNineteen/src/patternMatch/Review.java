package patternMatch;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Review {
	public static void main(String[] args) {
		String phoneList = """
                (800) 123-4567
                (800)123-4567
                (800) 123 4567
                800-123-4567
                800 123-4567
                800 123 4567
                8001234567
                """;

		Pattern phonePattern =
				Pattern.compile("\\(*[0-9]{3}[)\\s-]*[0-9]{3}[-\\s]*[0-9]{4}");

		Matcher phoneMatcher=phonePattern.matcher(phoneList);
		phoneMatcher.results().forEach(mRes-> System.out.println(mRes.group()));



	}
}
