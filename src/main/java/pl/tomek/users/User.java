package pl.tomek.users;

import pl.tomek.main.Validate;

import java.time.LocalDate;
import java.util.Scanner;

public class User {
    private final String name;
    private final String sureName;
    private final String peselId;
    private final LocalDate birthday;
    private int numberOfRentedCars;
    private boolean active;

    public void increaseNumberOfRentedCars() {
        this.numberOfRentedCars++;
    }

    public User(String name, String sureName, String peselId, int numberOfRentedCars) {
        this.name = name;
        this.sureName = sureName;
        this.peselId = peselId;
        this.birthday = parsePesel(peselId);
        this.numberOfRentedCars = numberOfRentedCars;
        this.active = false;
    }

    public String getNameAndSureName()
    {
        return name + " " + sureName;
    }

    private LocalDate parsePesel(String input)
    {
        int year = Integer.parseInt(input.substring(0,2));
        int month = Integer.parseInt(input.substring(2,4));
        if(month>20){
            year = 2000+year;
            month = month-20;
        }
        else
            year = 1900+year;
        int day = Integer.parseInt(input.substring(4,6));
        return LocalDate.of(year,month,day);
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public String showUser() {
        return "Name='" + name + '\'' +
                ", Sure name='" + sureName + '\'' +
                ", PESEL='" + peselId + '\'' +
                ", date of birthday=" + birthday +
                ", number of rented cars=" + numberOfRentedCars;
    }

    public static User build() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter name: ");
        String name = in.next();

        System.out.println("Enter last name: ");
        String sureName = in.next();

        System.out.println("Enter pesel: ");
        String pesel = in.next();

        while (!Validate.validatePesel(pesel))
        {
            System.out.println("Enter correct pesel (11 digits)!: ");
            pesel = in.next();
        }

        return new User(name,sureName,pesel,0);
    }
}
