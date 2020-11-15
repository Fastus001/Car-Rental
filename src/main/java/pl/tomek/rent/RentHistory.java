package pl.tomek.rent;

import pl.tomek.accounting.Account;
import pl.tomek.car.AvailableCars;
import pl.tomek.user.UserList;

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
            var user = userList.getUser(id);

            availableCars.showAvailableCars();
            System.out.println("Select car: ");
            int carId = in.nextInt();

            System.out.println("For how many days? ");
            int days = in.nextInt();

            var car = availableCars.getCar(carId);
            int disc = getDiscoutForUser(user);

            double d = disc/10.0;
            BigDecimal discount = BigDecimal.valueOf(d);
            System.out.println(discount);
            BigDecimal amount = car.getRentCost().multiply(BigDecimal.valueOf(days)).multiply(discount);
            System.out.print("Rent will cost "+ amount + "Are you sure ? [yes/no]");
            String yesOrNo = in.next();
            if(yesOrNo.contains("yes"))
            {
                availableCars.moveCarToRented(carId);
                addRent(new Rent(user,car,days));
                account.addAmount(amount);
                System.out.println("Car rented");
            }else
                System.out.println("Car not rented!!!");

    }

    private int getDiscoutForUser(pl.tomek.user.User user) {
        int disc = 10;
        if(user.getAge()>40)
            disc--;
        if(user.getNumberOfRentedCars()>100)
            disc--;
        return disc;
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
