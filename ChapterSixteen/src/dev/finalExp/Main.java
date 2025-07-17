package dev.finalExp;

import consumer.specific.ChildClass;
import dev.finalExp.gerneric.BaseClass;
import external.util.Logger;

public class Main {
    public static void main(String[] args) {
        BaseClass parent=new BaseClass();
        ChildClass child=new ChildClass();
        BaseClass childAsBase=new ChildClass();

        parent.recommendedMethod();
        System.out.println("_".repeat(50));
        childAsBase.recommendedMethod();
        System.out.println("_".repeat(50));
        child.recommendedMethod();



        System.out.println("_".repeat(50));

        parent.recommendedStatic();
        System.out.println("_".repeat(50));
        childAsBase.recommendedStatic();
        System.out.println("_".repeat(50));
        child.recommendedStatic();
        String xArg="This is all ";
        int y=16;
        StringBuilder ZArg=new StringBuilder("This is the additional Section ");
        doXYZ(xArg,y,ZArg);

        System.out.println(xArg);
        StringBuilder tracker=new StringBuilder("Step 1 is abc");
        Logger.logToConsole(tracker.toString());
        tracker.append(", Step 2 zyz. ");
        Logger.logToConsole(tracker.toString());
        System.out.println("After Logging tracker : "+tracker);

    }

    private static void doXYZ(String x ,int y,final StringBuilder z){
        String c=x+y;
        System.out.println("C -"+ c);



    }
}
