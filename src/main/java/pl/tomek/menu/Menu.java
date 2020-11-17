package pl.tomek.menu;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import pl.tomek.accounting.Account;
import pl.tomek.car.ListOfCars;
import pl.tomek.rent.Rent;
import pl.tomek.rent.RentCar;
import pl.tomek.rent.RentHistory;
import pl.tomek.user.UserList;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private final UserList userList;
    private final ListOfCars listOfCars;
    private final RentHistory rentHistory;
    private final Account account;
    private final ArrayList<Pair> listRunnable = new ArrayList<>();

    public Menu(UserList userList, ListOfCars listOfCars, RentHistory rentHistory) {
        this.userList = userList;
        this.listOfCars = listOfCars;
        this.rentHistory = rentHistory;
        account = new Account();
        addRunnable();
    }

    private void addRunnable()
    {
        listRunnable.add(new Pair("List available cars", showAvailableCars()));
        listRunnable.add(new Pair("Edit rental price", editRentalPrice()));
        listRunnable.add(new Pair("Add new car", addNewCar()));
        listRunnable.add(new Pair("List active users", listOfActiveUsers()));
        listRunnable.add(new Pair("Add user", addUser()));
        listRunnable.add(new Pair("Activate a user", activateUser()));
        listRunnable.add(new Pair("Rent a car", rentCar()));
        listRunnable.add(new Pair("List all rented cars", listAllRentedCars()));
        listRunnable.add(new Pair("Return a car", returnCar()));
        listRunnable.add(new Pair("Display monthly report", displayMonthlyReport()));
        listRunnable.add(new Pair("Display yearly report", displayYearlyReport()));
        listRunnable.add(new Pair("Exit", exit()));
    }

    public void showMenuText()
    {
        System.out.println("Welcome to car rental");
        for (int i = 0; i < listRunnable.size(); i++) {
            System.out.println((i+1)+": " + listRunnable.get(i).showText());
        }
        System.out.println("Choose your option:");
    }

    public void menuOptions()
    {
        showMenuText();
        try (Scanner inTwo = new Scanner(System.in)) {
            int input = inTwo.nextInt();
            while(true){
                if (input >= 0 && input <= listRunnable.size())
                    listRunnable.get(input - 1).runMethod();
                showMenuText();
                input = inTwo.nextInt();
            }
        }
    }

    static class Pair{
        private final String text;
        private final Runnable runnable;

        public Pair(String text, Runnable runnable) {
            this.text = text;
            this.runnable = runnable;
        }
        public String showText()
        {
            return text;
        }

        public void runMethod(){runnable.run();}
    }

    @NotNull
    @Contract(pure = true)
    private Runnable showAvailableCars(){return listOfCars::showAvailableCars;}

    @NotNull
    @Contract(pure = true)
    private Runnable editRentalPrice(){return listOfCars::editRentalPrice;}

    @NotNull
    @Contract(pure = true)
    private Runnable addNewCar(){return listOfCars::addNewCar;}

    @NotNull
    @Contract(pure = true)
    private Runnable listOfActiveUsers(){return userList::showAllActiveUsers;}

    @NotNull
    @Contract(pure = true)
    private Runnable addUser(){return userList::addNewUser;}

    @NotNull
    @Contract(pure = true)
    private Runnable activateUser(){return userList::setUserActive;}

    @NotNull
    @Contract(pure = true)
    private Runnable rentCar(){return ()->{
        RentCar rentCar = new RentCar( userList, listOfCars, account);
        Rent rent = rentCar.rentACar();
        if ( rent != null )
            rentHistory.addRent( rent);
    };}

    @NotNull
    @Contract(pure = true)
    private Runnable listAllRentedCars(){return rentHistory::showAllRentedCars;}

    @NotNull
    @Contract(pure = true)
    private Runnable returnCar(){return ()->rentHistory.abortRentals(listOfCars);}

    @NotNull
    @Contract(pure = true)
    private Runnable displayMonthlyReport(){return account::monthReport;}

    @NotNull
    @Contract(pure = true)
    private Runnable displayYearlyReport(){return account::yearReport;}

    @NotNull
    @Contract(pure = true)
    private Runnable exit(){return ()->System.exit(0);}
}

