package dev.lamdaChMini;

import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Main {
    public static void main(String[] args) {

        Consumer<String> consumer=new Consumer<>(){
            @Override
            public void accept(String sentence){
                String[] parts=sentence.split("");
                for (var part:parts){
                    System.out.println(part);
                }
            }
        };

        Consumer<String> consumer2=(s)-> {
            for (var temp :s.split("") ) {
                System.out.println(temp);
            }
        };

        UnaryOperator<String> secondLetter=(source)->{
            StringBuilder returnVal=new StringBuilder();
            for (int i = 0; i < source.length(); i++) {
                if(i%2==1){
                    returnVal.append(source.charAt(i));
                }
            }
            return returnVal.toString();
        };

        System.out.println(secondLetter.apply("1234567890"));
        System.out.println(everySecondCharacter("1234567890",secondLetter));

        Supplier<String> java=()->"i love Java";
        String supplierResult=java.get();
        System.out.println(supplierResult);

    }


    public static String everySecondChar(String source ) {
        StringBuilder returnVal = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            if (i % 2 == 1) {
                returnVal.append(source.charAt(i));
            }
        }
        return returnVal.toString();
    }
    public static String everySecondCharacter(String source,UnaryOperator<String> operator ){
        return operator.apply(source);
    }
}
