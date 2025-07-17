package dev.loc;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        System.out.println("def Locale= "+Locale.getDefault());

        Locale enSL=new Locale("en","SL");
        Locale enCA=new Locale("en","CA");

        Locale enIN=new Locale.Builder().setRegion("IN").setLanguage("en").build();
        Locale enNZ=new Locale.Builder().setRegion("NZ").setLanguage("en").build();

        var dtf= DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);

        for (Locale locale : List.of(Locale.getDefault(), Locale.UK, enSL, enIN, enCA, enNZ)) {
            System.out.println(locale.getDisplayName()+"="+ LocalDateTime.now().format(dtf.withLocale(locale)));
        }

        DateTimeFormatter wdayMoth=DateTimeFormatter.ofPattern("EEEE , MMMM ,d ,yyyy");

        LocalDate May5=LocalDate.of(2025,05,05);

        System.out.println("_".repeat(30));


        for (Locale locale : List.of(Locale.CANADA, Locale.CANADA_FRENCH, Locale.FRANCE,
                Locale.GERMANY, Locale.TAIWAN, Locale.JAPAN)) {
            System.out.println(locale.getDisplayName()+"="+
                    locale.getDisplayName(locale)+"=\n\t"+
                    May5.format(wdayMoth.withLocale(locale)));
            System.out.println(String.format(locale,"\t%1$tA %1$tB %1$te %1$tY %n",May5));
            NumberFormat decimalInfo=NumberFormat.getNumberInstance(locale);
            decimalInfo.setMaximumFractionDigits(7);
            System.out.println(decimalInfo.format(125874562.45845));

            NumberFormat currency=NumberFormat.getCurrencyInstance(locale);
            Currency localCurrency=Currency.getInstance(locale);

            System.out.println(currency.format(555.555)+"["+
                    localCurrency.getCurrencyCode()+"] "+
                    localCurrency.getDisplayName()+"/"+
                    localCurrency.getDisplayName(locale));
        }

        System.out.println(May5.format(wdayMoth));
        Scanner scanner=new Scanner(System.in);
        scanner.useLocale(Locale.ITALY);
        BigDecimal myLoan=scanner.nextBigDecimal();

        NumberFormat deciamlItaly=NumberFormat.getCurrencyInstance(Locale.ITALY);
        System.out.println("My Loan "+deciamlItaly.format(myLoan));




    }
}
