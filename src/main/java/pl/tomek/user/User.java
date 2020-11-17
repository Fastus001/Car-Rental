package pl.tomek.user;

import pl.tomek.main.Validate;

import java.time.LocalDate;
import java.util.Scanner;

public class User {
    private final String name;
    private final String surName;
    private final String userId;
    private final LocalDate birthday;
    private int numberOfRentedCars;
    private boolean active;

    public void increaseNumberOfRentedCars() {
        this.numberOfRentedCars++;
    }

    public User(String name, String surName, String userId, int numberOfRentedCars) {
        this.name = name;
        this.surName = surName;
        this.userId = userId;
        this.birthday = Validate.parsePeselID( userId );
        this.numberOfRentedCars = numberOfRentedCars;
        this.active = false;
    }

    public String getNameAndSureName()
    {
        return name + " " + surName;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public String showUser() {
        return new StringBuilder("Name= ")
                .append( name )
                .append( ", Surname= " )
                .append( surName )
                .append( ", PESEL = ")
                .append( userId )
                .append( ", date of birthday = " )
                .append( birthday )
                .append( ", number of rented cars = " )
                .append( numberOfRentedCars)
                .toString();
    }

    public static User buildUser() {
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

    public int getAge()
    {
        return LocalDate.now().getYear()-birthday.getYear();
    }

    public int getNumberOfRentedCars() {
        return this.numberOfRentedCars;
    }
}
