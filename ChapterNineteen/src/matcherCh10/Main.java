package matcherCh10;

import java.util.Arrays;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args) {


		String email="""
				john.boy@valid.com
				jane.doe-smith@valid.co.uk
				jane_Doe1976@valid.co.uk
				bob-1964@valid.net
				elaine@valid-test.com.au
				david@valid.io
				""";

		String wEmail= """
				john.boy@invalid
				bob!@invalid.com
				elaineinvalid1983@.com
				david@invalid..com
				""";

		Pattern emailPattern=Pattern.compile("[\\p{Alnum}-._]*@[a-z-]+[.][a-z].*");
		Matcher match=emailPattern.matcher(wEmail);

		email.lines()
				//.map(emailPattern::matcher)
				.forEach(s -> System.out.println(emailPattern.matcher(s).matches()+":\t"+s));

		System.out.println("-".repeat(50));
		wEmail.lines()
				//.map(emailPattern::matcher)
				.forEach(s -> System.out.println(emailPattern.matcher(s).matches()+":\t"+s));

//		match.results()
//				.map(s->Arrays.stream(email.split("\\R")))
//				.forEach(s-> System.out.println(match.matches()));
	}
}
