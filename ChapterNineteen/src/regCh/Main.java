package regCh;

public class Main {
    public static void main(String[] args) {

        String pattern ="Hello,World!";

        String para="Hello,World!";

        System.out.println(para.matches(pattern));


        //start uppercase... end .

        String pattern2="[A-Z][a-z\\s]*.$";
        String pattern3="[A-Z][a-z\\s]*\\.";//"[A-Z][a-z\\s]+[.]"

        String para2="The bike is a red.";
        System.out.println(para2.matches(pattern3));

        //end in punctuatuon

        String pattern4="[A-Z].*\\p{Punct}";

        String para3="I love being LPA a new student!";
        System.out.println(para3.matches(pattern4));


    }
}
