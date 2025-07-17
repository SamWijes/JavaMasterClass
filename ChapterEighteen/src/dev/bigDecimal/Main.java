package dev.bigDecimal;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {


        double policyAmount = 100_000_000;
        int beneficiaries = 3;
        float percentageFloat = 1.0f / beneficiaries;
        double percentage = 1.0 / beneficiaries;

        System.out.printf("Payout = %,.2f%n", policyAmount * percentageFloat);
        System.out.printf("Payout = %,.2f%n", policyAmount * percentage);

        Double totalUsingFloat = policyAmount -
                ((policyAmount * percentageFloat) * beneficiaries);

        System.out.printf("totalUsingFloat: %,.2f%n", totalUsingFloat);

        double total = policyAmount -
                ((policyAmount * percentage) * beneficiaries);
        System.out.printf("total: %,.2f%n", total);
        String[] tests = {"15.456", "8", "10000.000001", ".123"};
        BigDecimal[] bds=new BigDecimal[tests.length];
        Arrays.setAll(bds,i->new BigDecimal(tests[i]));

        System.out.printf("%-14s %-15s %-8s %s%n", "Value", "Unscaled Value", "Scale",
                "Precision");

        for (BigDecimal bd : bds) {
            System.out.printf("%-14s %-15d %-8d %d%n", bd, bd.unscaledValue(), bd.scale(),
                    bd.precision());
        }

        double[] doubles = {15.456, 8, 10000.000001, .125};
        Arrays.setAll(bds,i-> BigDecimal.valueOf(doubles[i]));

        System.out.printf("%-14s %-15s %-8s %s%n", "Value", "Unscaled Value", "Scale",
                "Precision");

        for (BigDecimal bd : bds) {
            System.out.printf("%-14s %-15d %-8d %d%n", bd, bd.unscaledValue(), bd.scale(),
                    bd.precision());
            bd=bd.setScale(2,RoundingMode.HALF_UP);
            System.out.printf("%-14s %-15d %-8d %d%n", bd, bd.unscaledValue(), bd.scale(),
                    bd.precision());
        }

        BigDecimal policyPayout=new BigDecimal("100000000.00");
        System.out.printf("%-14s %-15d %-8d %d%n", policyPayout, policyPayout.unscaledValue(), policyPayout.scale(),
                policyPayout.precision());

        BigDecimal percent=BigDecimal.ONE.divide(BigDecimal.valueOf(beneficiaries),
                new MathContext(60,RoundingMode.UP));
        System.out.println(percent);
    }
}
