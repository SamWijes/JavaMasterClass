package consumer.specific;

import dev.finalExp.gerneric.BaseClass;

public class ChildClass extends BaseClass {

//    @Override
//    public void recommendedMethod() {
//        System.out.println("[child:Recomment]:My Method");
//        optionalMethod();
//        // super.recommendedMethod();
//    }
    @Override
    protected void optionalMethod() {
        System.out.println("[child:optionalmethod] optional stufff!!!!");
        super.optionalMethod();
    }

    public static void recommendedStatic() {
        System.out.println("[Child.recommendedStatic]:Best Static");
        optionalStatic();
//        mandatoryStatic();
    }

}
