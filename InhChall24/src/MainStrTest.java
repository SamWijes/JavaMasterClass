public class MainStrTest {

    public static void main(String[] args) {
        StringInformation("sammy");
        String testStr="";
        String l= (testStr.isEmpty()) ? "yes":"something else"  ;
        System.out.println(l);
    }

    public static void StringInformation(String string){
        int length=string.length();
        System.out.printf("String has length of %d %n",length);
        System.out.printf("String Starts with %c %n ",string.charAt(0));
        System.out.printf("String Ends with %c %n ",string.charAt(length-1));

    }


}
