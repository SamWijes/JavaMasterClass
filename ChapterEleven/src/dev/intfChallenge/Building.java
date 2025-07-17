package dev.intfChallenge;

public class Building extends Landmark{
    private String name;
    private String usage;
    public Building(String icon,String name,String usage) {
        super(icon,"POINT", "%s(%s)".formatted(name,usage));
        this.name=name;
        this.usage=usage;
    }

    @Override
    public String toString() {
        String str= """
                "type" :"%s", "label" : "%s" ,"marker" : "%s","name" : "%s" ,"usage" : "%s" """.
                formatted(geometryType(),label(),iconType(),name,usage);
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
