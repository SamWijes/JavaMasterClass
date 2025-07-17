package dev.SortedMaps;

import java.sql.ClientInfoStatus;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static Map<String,Purchase> purchases=new LinkedHashMap<>();
    public static NavigableMap<String,Student> students=new TreeMap<>();
    public static void main(String[] args) {
        Course jmc=new Course("JMC101","JMaster","java");
        Course py=new Course("pyi101","PythonB1 ","python");
        addPurchase("Sam Wije",jmc,45.59);
        addPurchase("Sam Wije",py,45.59);
        addPurchase("Mary Jane",jmc,45.59);
        addPurchase("Joe Foster",jmc,45.59);
        addPurchase("Randy Webber",py,45.59);
        addPurchase("Jack Rain",py,45.59);
        addPurchase("Sam Wije",jmc,45.59);
        addPurchase("Soorn Wije",py,45.59);
        addPurchase("Mike Witman",jmc,45.59);
        addPurchase("Carly Freeman",jmc,45.59);
        addPurchase("Jimmy Rite",py,45.59);
        addPurchase("Anne Rand",py,45.59);

        //purchases.forEach((s, purchase) -> System.out.println(s+" : "+purchase));

        /// print key date:list of purchases on date
        NavigableMap<LocalDate,List<Purchase>> dateOfPurchase=new TreeMap<>();

        for (Purchase p:purchases.values()){
            dateOfPurchase.compute(p.purchaseDate(),
                    (pDate,pList)->{
                    List<Purchase>list= (pList==null)?new ArrayList<>():pList;
                    list.add(p);
                    return list;
            });

        }
        dateOfPurchase.forEach((k,v)-> System.out.println(k+":"+v));

        int currentYear=LocalDate.now().getYear();

        LocalDate firstDay=LocalDate.ofYearDay(currentYear,1);
        LocalDate week1=firstDay.plusDays(7);
        Map<LocalDate,List<Purchase>> week1Purchases=dateOfPurchase.headMap(week1);
        Map<LocalDate,List<Purchase>> week2Purchases=dateOfPurchase.tailMap(week1);

        System.out.println("_".repeat(20));
        //System.out.println(week1Purchases);
        week1Purchases.forEach((k,v)-> System.out.println(k+":"+v));

        displayStat(1,week1Purchases);
        displayStat(2,week2Purchases);
        LocalDate lastDate=dateOfPurchase.lastKey();
        var prevEntry=dateOfPurchase.lastEntry();
        while (prevEntry!=null){
            LocalDate prevDate=dateOfPurchase.lowerKey(lastDate);
            List<Purchase> purchases=prevEntry.getValue();
            prevEntry=dateOfPurchase.lowerEntry(lastDate);
            System.out.println(lastDate +": purchases "+ purchases.size());
            lastDate=prevDate;
        }

    }


    public static void addPurchase(String name,Course course, double price){
        Student existingStudent=students.get(name);
        if (existingStudent==null){
            existingStudent=new Student(name,course);
            students.put(name,existingStudent);
        }else existingStudent.addCourse(course);

        int day=new Random().nextInt(1,15);
        String key=course.courseID()+"_"+existingStudent.getId();
        int year= LocalDate.now().getYear();
        Purchase purchase=new Purchase(course.courseID(), existingStudent.getId(),price,year,day);
        purchases.put(key,purchase);

        //get last element key from datedpur





    }

    //displaystat method-weekly/period count also should give total course count at end

    public static void displayStat(int period,Map<LocalDate,List<Purchase>> periodData){
        System.out.println("_".repeat(30));
        Map<String,Integer> weeklyCounts=new TreeMap<>();

        periodData.forEach((key,value)->{
            System.out.println(key+":"+value);
            for(Purchase p:value){
                weeklyCounts.merge(p.courseID(),1,(prev,curr)->prev+curr);
            }
        });

        System.out.printf("Week %d purchases %s%n",period,weeklyCounts);
    }

}
