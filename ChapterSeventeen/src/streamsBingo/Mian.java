package streamsBingo;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Mian {
    public static void main(String[] args) {

        List<String> bingoPool=new ArrayList<>(75);

        int start=1;
        for (char c : "BINGO".toCharArray()) {
            for (int i = start; i < (start + 15); i++) {
                bingoPool.add(c+"-"+i);

            }
            start+=15;
        }

        Collections.shuffle(bingoPool);

//        var firstSet=bingoPool.subList(0,15);
//        System.out.println(firstSet);
//        firstSet.sort(Comparator.naturalOrder());
//        firstSet.replaceAll(s->{
//            if (s.indexOf('G')==0 ||s.indexOf('O')==0 ){
//                String updated=s.charAt(0)+"-"+s.substring(2);
//                System.out.print(updated +" ");
//                return updated;
//            }
//            return s;
//
//        });

        var testStream=bingoPool.stream()
                .limit(15)
                .filter(s->s.indexOf('G')==0||s.indexOf('O')==0)
                .map(s -> s.charAt(0)+""+s.substring(1))
                .sorted();
        testStream.forEach(s -> System.out.print(s+" "));
        System.out.println("");
            //error
        String[] strings={"one","two","three"};
        var firstStream= Arrays.stream(strings)
                .sorted(Comparator.reverseOrder());
//                .forEach(System.out::println)


        var secondStream = Stream.of("Six","Five","Four")
                .map(String::toUpperCase);
//                .forEach(System.out::println);
       // System.out.println(firstSet);

        Stream.concat(secondStream, firstStream)
                .map(s -> s.charAt(0) + " - " + s)
                .forEach(System.out::println);

        System.out.println();


        IntStream.iterate(1,n->n+1)
                .filter(s->s%2==0)
                .limit(15)
                .forEach(System.out::println);


    }
}
