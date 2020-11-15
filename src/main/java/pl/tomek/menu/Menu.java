package pl.tomek.menu;

import pl.tomek.cars.AvailableCars;
import pl.tomek.main.Greetings;
import pl.tomek.rent.Rent;
import pl.tomek.rent.RentHistory;
import pl.tomek.users.UserList;

import java.math.BigDecimal;
import java.util.Scanner;

public class Menu {
    UserList userList;
    AvailableCars availableCars;
    RentHistory rentHistory;

    public Menu(UserList userList, AvailableCars availableCars, RentHistory rentHistory) {
        this.userList = userList;
        this.availableCars = availableCars;
        this.rentHistory = rentHistory;
    }

    public void showMenuText()
    {
        System.out.println(Greetings.GREETINGS);
    }
    
    public void menuOptions()
    {
        showMenuText();
        Scanner in = new Scanner(System.in);
        int input = in.nextInt();
        while (input!=12)
        {
            switch (input)
            {
                case 1: availableCars.showAvailableCars();break;
                case 2: availableCars.editRentalPrice();break;
                case 3: availableCars.addNewCar();break;
                case 4: userList.showAllActiveUsers();break;
                case 5: userList.addNewUser();break;
                case 6: userList.setUserActive();break;
                case 7: rentACar();break;
                case 8: rentHistory.showAllRentedCars();break;
                case 9: break;
                case 10: break;
                case 11: break;
            }
            showMenuText();
            input = in.nextInt();
        }
    }

    private void rentACar() {
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
        System.out.printf("Rent will cost "+ car.getRentCost().multiply(BigDecimal.valueOf(days)) + "Are you sure ? [yes/no]");
        String yesOrNo = in.next();
        if(yesOrNo.contains("yes"))
        {
            availableCars.moveCarToRented(carId);
            rentHistory.addRent(new Rent(userList.getUser(id),car,days));
            System.out.println("Car rented");
        }else
            System.out.println("Car not rented!!!");

    }


}
