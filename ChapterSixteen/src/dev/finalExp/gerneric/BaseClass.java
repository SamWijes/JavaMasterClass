package dev.finalExp.gerneric;

public class BaseClass {
    public final int mynum=123;
    public final void recommendedMethod() {
        System.out.println("[BaseClass.recommendedMethod]:Best method");
        optionalMethod();
        mandatoryMethod();
    }

    protected void optionalMethod() {
        System.out.println("[BaseClass.optionalMethod]:customizable method");
    }

    private void mandatoryMethod() {
        System.out.println("[BaseClass.mandatoryMethod]:Must Implement!!!!");
    }

    public static void recommendedStatic() {
        System.out.println("[BaseClass.recommendedStatic]:Best Static");
        optionalStatic();
        mandatoryStatic();
    }

    protected static void optionalStatic() {
        System.out.println("[BaseClass.optionalStatic]:customizable Static");
    }

    private static void mandatoryStatic() {
        System.out.println("[BaseClass.mandatoryStatic]:Must Implement!!!!");
    }


}
