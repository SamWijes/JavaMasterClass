package dev.external;

import dev.immutableClasses.PersonImmutable;

public class MainImmutable {
    public static void main(String[] args) {
        PersonImmutable jane=new PersonImmutable("Jane","01/01/1930");
        PersonImmutable jim=new PersonImmutable("Jim","02/05/1945");
        PersonImmutable joe=new PersonImmutable("Joe","02/05/1925");


        PersonImmutable[] jhonskids= {jane, jim, joe};
        PersonImmutable john=new PersonImmutable("John","02/05/1900",jhonskids);
        System.out.println(john);

        PersonImmutable[] kids=john.getKids();
        kids[0]=jim;
        kids[1]=new PersonImmutable("Ann","02/05/1945");
        System.out.println(john);
        jhonskids[0]=new PersonImmutable("Rose","02/05/1945");
        System.out.println(john);

    }
}
