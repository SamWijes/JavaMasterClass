import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String helloWorld = "%s %s".formatted("Hello", "World");
        String helloWorld2 = String.format("%s %s", "Hello", "World");
        System.out.println("Using string's formatted method: " + helloWorld);
        System.out.println("Using String.format: " + helloWorld2);


        System.out.println(format("%s %s","hello","world"));
        String testString = "Anyone can Learn abc's, 123's, and any regular expression";
        String replacement = "(-)";

        String[] patterns = {"[a-zA-Z]*$","[0-9]","[aA]ne   \\b"};

        for (String pattern : patterns) {
            String output=testString.replaceFirst(pattern,replacement);
            System.out.println("Parrern "+pattern+"=>"+output);
        }


        // Song of the Witches in MacBeth, a Play by Shakespeare
        String paragraph = """
                Double, double toil and trouble;
                Fire burn and caldron bubble.
                Fillet of a fenny snake,
                In the caldron boil and bake
                Eye of newt and toe of frog,
                Wool of bat and tongue of dog,
                Adder's fork and blind-worm's sting,
                Lizard's leg and howlet's wing,
                For a charm of powerful trouble,
                Like a hell-broth boil and bubble.
                """;

        String[] lines=paragraph.split("\\R");
        System.out.println("This paragraph has "+lines.length+" lines");
        String[] words=paragraph.split("\\s");
        System.out.println("This paragraph has " + words.length + " words");
        System.out.println(paragraph.replaceAll("[a-zA-Z]+ble","[GRUB]"));

        Scanner scanner=new Scanner(paragraph);
        System.out.println(scanner.delimiter());
        scanner.useDelimiter("\\R");

//        while (scanner.hasNext()){
//            String element= scanner.next();
//            System.out.println(element);
//        }
//        scanner.tokens()
//                .map(s -> s.replaceAll("\\p{Punct}",""))
//                .flatMap(s->Arrays.stream(s.split("\\s+")))
//                .filter(s -> s.matches("[a-zA-Z]+ble"))
//                .forEach(System.out::println);


        System.out.println(scanner.findInLine("[a-zA-Z]+ble"));
        System.out.println(scanner.findInLine("[a-zA-Z]+ble"));
        scanner.close();

    }

    private static String format(String regexp,String ...args){
//        String compString="";
//
//        List<String> arrayArg=List.of(args);
//        if (regexp.contains("%s")){
//           for (var str:arrayArg) {
//               //System.out.println(str);
//               compString = regexp.replaceFirst("%s",str);
//               regexp=compString;
//           }
//
//        }
        //regexp=compString;


        int index=0;
        while (regexp.matches(".*%s.*")){
            regexp=regexp.replaceFirst("%s",args[index++]);
        }
        return regexp;
    }
}