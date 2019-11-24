package ui;

import model.Contact;
import model.ContactType;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        System.out.println("To add a new contact, type in their name, then press 'enter':");
        String name = s.next();
        System.out.println("Enter number and press 'enter':");
        String number = s.next();
        System.out.println("Enter type 1. for work, 2. for friend, 3 for family and press 'enter':");
        String type_string = s.next();
        ContactType type = ContactType.FAMILY;
        switch (type_string) {
            case "1":
                type = ContactType.WORK;
                break;
            case "2":
                type = ContactType.FRIEND;
                break;
            case "3":
                type = ContactType.FAMILY;
        }
        Contact c = new Contact(name, number, type);
        System.out.println("Would you like to call " + c.getName() + " now? Type 'y' for yes, 'n' for no.");
        options(c, s);
    }

    private static void options(Contact c, Scanner s){
        String option = s.next();
        if(option.equals("y")) {
            System.out.println("Enter a date for the call in the format MM/DD/YY:");
            c.call(s.next());
            System.out.println("Here is your updated call history:");
            c.getCallLog().forEach(System.out::println);
        } else if (option.equals("n")) {
            System.out.println("Call history: No Calls.");
        }
    }


}
