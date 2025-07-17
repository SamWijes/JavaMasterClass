public class Ternary {

    public static void main(String[] args) {
        String result=(5>6) ? "yes":"no";
        System.out.println((5>6)?"Yes":"No");


        int x = 10;
       // (x > 0) ? System.out.println("Positive") : System.out.println("Negative or Zero");
        String s = (x > 0) ? positiveAction() : negativeAction();
        System.out.println(s);
    }
    public static String positiveAction() {
        return "x is positive!";
    }

    public static String negativeAction() {
        return "x is zero or negative!";
    }

}
