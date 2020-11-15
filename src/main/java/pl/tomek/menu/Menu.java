package pl.tomek.menu;

import pl.tomek.accounting.Account;
import pl.tomek.car.AvailableCars;
import pl.tomek.main.Greetings;
import pl.tomek.rent.RentHistory;
import pl.tomek.user.UserList;

import java.util.Scanner;

public class Menu {
    private final UserList userList;
    private final AvailableCars availableCars;
    private final RentHistory rentHistory;
    private final Account account;

    public Menu(UserList userList, AvailableCars availableCars, RentHistory rentHistory) {
        this.userList = userList;
        this.availableCars = availableCars;
        this.rentHistory = rentHistory;
        account = new Account();
    }

    public void showMenuText()
    {
        System.out.println(Greetings.GREETINGS);
    }
    
    public void menuOptions()
    {
        showMenuText();
        try (Scanner in = new Scanner(System.in)) {
            int input = in.nextInt();
            while (input != 12) {
                switch (input) {
                    case 1:availableCars.showAvailableCars();
                        break;
                    case 2: availableCars.editRentalPrice();
                        break;
                    case 3: availableCars.addNewCar();
                        break;
                    case 4:userList.showAllActiveUsers();
                        break;
                    case 5: userList.addNewUser();
                        break;
                    case 6: userList.setUserActive();
                        break;
                    case 7: rentHistory.rentACar(userList, availableCars, account);
                        break;
                    case 8:rentHistory.showAllRentedCars();
                        break;
                    case 9:rentHistory.abortRentals(availableCars);
                        break;
                    case 10:account.monthReport();
                        break;
                    case 11: account.yearReport();
                        break;
                }
                showMenuText();

                input = in.nextInt();

                if(input == 12)
                    in.close();
            }
        }
    }
}
