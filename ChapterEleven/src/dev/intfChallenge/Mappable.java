package dev.intfChallenge;

public interface Mappable {
    String JSON_PROPERTY="\"Properties:{%s}\"";


    String label();
    String geometryType();
    String iconType();

    static void detail(Mappable mappable){
        System.out.println(mappable);

    }


    default void toJSON(){
        String str= """
                "type" :"%s", "label" : "%s" ,"marker" : "%s" """.formatted(this.geometryType(),this.label(),this.iconType());

        System.out.printf(JSON_PROPERTY,str);
        System.out.println();

    }
}


abstract class Landmark implements Mappable{
    protected String icon;
    protected String geometry;
    protected String label;


    public Landmark(String icon, String geometry, String label) {
        this.icon = icon;
        this.geometry = geometry;
        this.label = label;
    }
}