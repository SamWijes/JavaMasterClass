import java.util.InputMismatchException;
import java.util.Scanner;

public class ReadChl {

    public static void main(String[] args) {
/*
        Scanner scanner=new Scanner(System.in);
        int startNum=1;
        int sum = 0;
        do {
            System.out.println("Enter Number#"+startNum);
            try {
                int number=Integer.parseInt(scanner.nextLine());
                sum+=number;
                startNum++;
                }catch (NumberFormatException e){
                System.out.println("Error Try Again");
            }

            }while (startNum<=3);
        System.out.println("sum of numbers - "+sum);

        scanner.close();
*/

        int maxNum=0;
        int minNum=0;
        Scanner scanner=new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Enter Number ");
                int numberInput = scanner.nextInt();
                if (numberInput>maxNum){
                    maxNum=numberInput;
                }
                if(minNum==0 && numberInput<maxNum && numberInput!=0) {
                    minNum=numberInput;
                }
                else if (numberInput<minNum) {
                    minNum=numberInput;
                }
            } catch (InputMismatchException ime) {
                System.out.println("Invalid Input Exiting Loop");
                System.out.println("max num "+maxNum);
                System.out.println("min num "+minNum);
                break;
            }

        }
        scanner.close();

    }




}
