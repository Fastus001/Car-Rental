package pl.tomek.rent;

import org.jetbrains.annotations.NotNull;
import pl.tomek.accounting.Account;
import pl.tomek.car.Car;
import pl.tomek.car.ListOfCars;
import pl.tomek.user.User;
import pl.tomek.user.UserList;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Scanner;

public class RentCar {
    private final UserList userList;
    private final ListOfCars listOfCars;
    private final Account account;
    private int carId;

    public RentCar(UserList userList, ListOfCars listOfCars, Account account) {
        this.userList = userList;
        this.listOfCars = listOfCars;
        this.account = account;
    }

    public Rent rentACar() {
        Scanner in = new Scanner( System.in);
        var user = getUser(in);
        var car = getCar(in);
        int days = forHowManyDays( in );

        BigDecimal amount = getAmountForRent( car, days, getDiscountForUser( user ) );
        System.out.print( "Rent will cost " + NumberFormat.getCurrencyInstance().format( amount) + ". Are you sure ? [yes/no]" );
        String yesOrNo = in.next();
        if ( yesOrNo.contains( "yes" ) ) {
            listOfCars.moveCarToRented( carId );
            account.addAmount( amount );
            System.out.println( "Car rented" );
            return new Rent( user, car, days );
        }
        else
            System.out.println( "Car not rented!!!" );
        return null;
    }

    @NotNull
    private BigDecimal getAmountForRent(Car car, int days, int disc) {
        double d = disc / 10.0;
            return car.getRentCost().multiply( BigDecimal.valueOf( days ) ).multiply( BigDecimal.valueOf( d ) );
    }

    private int forHowManyDays(Scanner in)
    {
        System.out.println( "For how many days? " );
        return in.nextInt();
    }

    private User getUser(Scanner in) {
        userList.showAllActiveUsers();
        System.out.println( "Select user by ID: " );
            return userList.getUser( in.nextInt() );
    }

    private Car getCar(Scanner in)
    {
        listOfCars.showAvailableCars();
        System.out.println( "Select car: " );
        this.carId = in.nextInt();
            return listOfCars.getCar( carId );
    }

    private int getDiscountForUser(@NotNull User user) {
        int disc = 10;
        if(user.getAge()>40)
            disc--;
        if(user.getNumberOfRentedCars()>100)
            disc--;
        return disc;
    }
}
