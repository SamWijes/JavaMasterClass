public class ReadingInput32 {

    public static void main(String[] args) {

        int currentYear = 2024;

        System.out.println(getInputFromConsole(currentYear));

        System.out.println(getInputFromScanner(currentYear));
    }

    public static String getInputFromConsole(int currentYear) {

        String name = System.console().readLine("Hi What's ur name? ");
        System.out.println("Hi " + name + ", Thanks for taking the course!");

        String dateOfBirth = System.console().readLine("What year were u born?");
        int age = currentYear-Integer.parseInt(dateOfBirth);
        return "So you are " + age + " years old";
    }

    public static String getInputFromScanner(int currentYear) {

        return "";
    }
}
