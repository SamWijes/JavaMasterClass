package dev.genChallenge.Util;

import dev.genChallenge.Model.SamStudent;

import java.util.Comparator;

public class SamComparator implements Comparator<SamStudent> {

    @Override
    public int compare(SamStudent o1, SamStudent o2) {
        return (int) (o1.getPercentageComplete()- o2.getPercentageComplete());
    }
}
