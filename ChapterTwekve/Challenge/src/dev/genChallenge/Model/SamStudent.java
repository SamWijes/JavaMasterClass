package dev.genChallenge.Model;

public class SamStudent extends Student {
    private double percentageComplete;
    public SamStudent(){
        this.percentageComplete=random.nextDouble(0,100.001);
    }

    @Override
    public String toString() {
        return super.toString() +"%10.2f%%".formatted(percentageComplete);
    }

    public void setPercentageComplete(double percentageComplete) {
        this.percentageComplete = percentageComplete;
    }

    public double getPercentageComplete() {
        return percentageComplete;
    }

    @Override
    public boolean matchFieldValue(String fieldName, String value) {
        if(fieldName.equalsIgnoreCase("percentagecomplete")) {
            return percentageComplete <= Integer.parseInt(value);
        }else return super.matchFieldValue(fieldName, value);
    }
}
