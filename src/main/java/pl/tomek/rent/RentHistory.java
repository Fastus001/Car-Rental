package pl.tomek.rent;

import org.jetbrains.annotations.NotNull;
import pl.tomek.accounting.Account;
import pl.tomek.car.ListOfCars;
import pl.tomek.user.UserList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class RentHistory {
    private final ArrayList<Rent> rentHistory = new ArrayList<>();

    public void addRent(Rent rent){ this.rentHistory.add( rent ); }

    public void showAllRentedCars()
    {
        System.out.println("Active rentals:");

        for (int i = 0; i < rentHistory.size(); i++) {
                System.out.println((i+1)+": " + rentHistory.get(i).showCars());
        }
    }

    public void abortRentals(ListOfCars listOfCars) {
        showAllRentedCars();
        int id;
        Scanner in = new Scanner(System.in);
            System.out.println("Which rent should be aborted? ");
            id = in.nextInt();

        listOfCars.moveCarToNotRented(id);
        rentHistory.remove(id-1);
        System.out.println("Rental aborted");
    }
}
