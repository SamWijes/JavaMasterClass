package dev.intfChallenge;


public class UtilityLine extends Landmark {
    private String name;
    private String utility;


    public UtilityLine(String icon, String name, String utility) {
        super(icon,"LINE", "%s(%s)".formatted(name,utility));
        this.name=name;
        this.utility=utility;
    }

    @Override
    public String toString() {
        String str= """
                "type" :"%s", "label" : "%s" ,"marker" : "%s","name" : "%s" ,"utility" : "%s" """.
                formatted(geometryType(),label(),iconType(),name,utility);
        return JSON_PROPERTY.formatted(str);
    }

    @Override
    public String label() {
        return label;
    }

    @Override
    public String geometryType() {
        return geometry;
    }

    @Override
    public String iconType() {
        return icon;
    }
}
