package pl.tomek.rent;

import pl.tomek.accounting.Account;
import pl.tomek.cars.AvailableCars;
import pl.tomek.users.UserList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class RentHistory {
    private final ArrayList<Rent> rentHistory = new ArrayList<>();

    private void addRent(Rent rent)
    {
        this.rentHistory.add(rent);
    }

    public void showAllRentedCars()
    {
        System.out.println("Active rentals:\n");

        for (int i = 0; i < rentHistory.size(); i++) {
                System.out.println((i+1)+": " + rentHistory.get(i).showCars());
        }
    }

    public void rentACar(UserList userList, AvailableCars availableCars, Account account) {
        userList.showAllActiveUsers();
        Scanner in = new Scanner(System.in);
            System.out.println("Select user by ID: ");
            int id = in.nextInt();

            availableCars.showAvailableCars();
            System.out.println("Select car: ");
            int carId = in.nextInt();

            System.out.println("For how many days? ");
            int days = in.nextInt();

            var car = availableCars.getCar(carId);
            BigDecimal amount = car.getRentCost().multiply(BigDecimal.valueOf(days));
            System.out.print("Rent will cost "+ amount + "Are you sure ? [yes/no]");
            String yesOrNo = in.next();
            if(yesOrNo.contains("yes"))
            {
                availableCars.moveCarToRented(carId);
                addRent(new Rent(userList.getUser(id),car,days));
                account.addAmount(amount);
                System.out.println("Car rented");
            }else
                System.out.println("Car not rented!!!");


    }


    public void abortRentals(AvailableCars availableCars) {
        showAllRentedCars();
        int id;
        Scanner in = new Scanner(System.in);
            System.out.println("Which rent should be aborted? ");
            id = in.nextInt();

        availableCars.moveCarToNotRented(id);
        rentHistory.remove(id-1);
        System.out.println("Rental aborted");
    }
}
