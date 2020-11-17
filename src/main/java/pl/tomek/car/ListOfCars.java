package pl.tomek.car;

import pl.tomek.main.Validate;

import java.util.ArrayList;
import java.util.Scanner;

public class ListOfCars {
    private final ArrayList<Car> availableCars = new ArrayList<>();
    private final ArrayList<Car> notAvailableCars = new ArrayList<>();
    
    public void addCar(Car car)
    {
        if(availableCars.size() + notAvailableCars.size()<100)
            availableCars.add(car);
        else
            System.out.println("Maximum capacity of cars in our company reached!");
    }
    
    public void showAvailableCars()
    {
        if(availableCars.size()==0)
            System.out.println("There is no available car to rent!");
        else {
            for (int i = 0; i < availableCars.size(); i++) {
                System.out.println((i + 1) + ": " + availableCars.get(i).showCar());
            }
        }
    }

    public void moveCarToRented(int index)
    {
        notAvailableCars.add(availableCars.remove(index-1));
    }

    public void moveCarToNotRented(int index)
    {
        availableCars.add(notAvailableCars.remove(index-1));
    }

    public void editRentalPrice()
    {
        showAvailableCars();
        Scanner in = new Scanner(System.in);
        System.out.println("Give item number to change rent price");
        int number = in.nextInt();
        System.out.println("Give new rental price: ");
        availableCars.get(number-1).setRent(in.nextBigDecimal());
    }

    public void addNewCar()
    {
        Car car = Validate.validateNewCar();
        if(car != null)
            availableCars.add(car);
    }

    public Car getCar(int i) {
        return availableCars.get(i-1);
    }
}
