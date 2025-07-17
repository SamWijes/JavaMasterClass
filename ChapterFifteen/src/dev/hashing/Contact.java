package dev.hashing;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Contact {
    String name;
    Set<String > emails=new HashSet<>();
    Set<String > phones=new HashSet<>();

    public Contact(String name) {
        this(name,null);
    }

    public Contact(String name, String email) {
        this(name,email,0);
    }

    public Contact(String name, long phone) {
        this(name,null,phone);
    }
    public Contact(String name, String email, long phone) {
        this.name = name;
        if(email!=null){emails.add(email);}
        if (phone > 0) {
            String phoneStr = String.valueOf(phone);
            String phoneStrFormat = "(%s) %s-%s".formatted(phoneStr.substring(0, 3), phoneStr.substring(3, 6),
                    phoneStr.substring(6));

            phones.add(phoneStrFormat);
        }
    }

    public String getName() {
        return name;
    }

    public String getNameLastFirst(){
        return name.substring(name.indexOf(" ")+1)+", "+name.substring(0,name.indexOf(" "));
    }



    @Override
    public String toString() {
        return "%s: %s %s".formatted(name,emails,phones);
    }

    public Contact mergerContactData(Contact contact){
        Contact temp=new Contact(contact.getName());
        temp.emails.addAll(contact.emails);
        temp.emails.addAll(this.emails);
        temp.phones.addAll(contact.phones);
        temp.phones.addAll(this.phones);
        return temp;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        return getName().equals(contact.getName());
    }

    @Override
    public int hashCode() {
        return 33* getName().hashCode();
    }

    public void addEmail(String companyName){
        String[] names=name.split(" ");
        String email="%c%s@%s.com".formatted(names[0].charAt(0),names[names.length-1],
                companyName.replaceAll(" ","").toLowerCase());
        if(emails.add(email)) System.out.println(name+ " Already has email " +email );
        else if (!emails.add(email)) System.out.println(name+ " now  has email " +email);
    }

    public void replaceEmailIfExists(String currentEmail,String newEmail){
        if (emails.contains(currentEmail)) {
            emails.remove(currentEmail);
            emails.add(newEmail);
        }
    }
}
