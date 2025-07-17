package dev.generic08;



public class SamStudent extends Student {
    private double percentageComplete;
    public SamStudent(){
        this.percentageComplete=random.nextDouble(0,100.01);
    }

    @Override
    public String toString() {
        return super.toString() +"%10.2f%%".formatted(percentageComplete);
    }

    public void setPercentageComplete(double percentageComplete) {
        this.percentageComplete = percentageComplete;
    }
}
