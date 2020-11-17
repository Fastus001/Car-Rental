package pl.tomek.main;

import pl.tomek.car.ListOfCars;
import pl.tomek.car.Car;
import pl.tomek.menu.Menu;
import pl.tomek.rent.RentHistory;
import pl.tomek.user.User;
import pl.tomek.user.UserList;

public class Main {
    public static void main(String[] args) {
        Car car = new Car("Opel","Astra", 125000,1990,12,12,23);
        Car car2 = new Car("Mercedes","CLA", 12500,2017,2,12,1245.59);
        Car car3 = new Car("Honda","Accord", 1254500,2008,5,24,245.50);
        User user1 = new User("Tomek", "Nowak", "81121502995",2);
        User user2 = new User("Ala", "Nowak", "05221502995",0);

        UserList userList = new UserList();
        userList.addUser(user1);
        userList.addUser(user2);
        ListOfCars listOfCars = new ListOfCars();
        listOfCars.addCar(car);
        listOfCars.addCar(car2);
        listOfCars.addCar(car3);

        RentHistory rentHistory = new RentHistory();
        Menu menu = new Menu(userList, listOfCars, rentHistory);
        menu.menuOptions();

    }
}
