package pl.tomek.cars;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AvailableCars {
    private ArrayList<Car> availableCars = new ArrayList<>();
    private ArrayList<Car> notAvailableCars = new ArrayList<>();

    public AvailableCars() {
        this.availableCars = availableCars;
    }
    
    public void addCar(Car car)
    {
        if(availableCars.size()<100)
            availableCars.add(car);
        else
            System.out.println("Została osiągnięta maksymalna ilość samochodó w wypożyczalni.");
    }
    
    public void showAvailableCars()
    {
        for (int i = 0; i < availableCars.size(); i++) {
                System.out.println(Integer.toString(i+1)+": " + availableCars.get(i).showCar());
        }
    }

    public void showNotAvailableCars()
    {
        for (int i = 0; i < notAvailableCars.size(); i++) {
                System.out.println(Integer.toString(i+1)+": " + notAvailableCars.get(i).showCar());
        }
    }

    public void moveCarToRented(int index)
    {
        Car car = availableCars.get(index-1);
        availableCars.remove(index-1);
        notAvailableCars.add(car);
    }

    public void editRentalPrice()
    {
        showAvailableCars();
        Scanner in = new Scanner(System.in);
        System.out.println("Give item number to change rent price");
        int number = in.nextInt();
        System.out.println("Give new rental prive: ");
        double newPrice = in.nextDouble();
        availableCars.get(number-1).setRent(newPrice);
    }

    public void addNewCar()
    {
        availableCars.add(Car.makeNewCar());
    }


    public Car getCar(int i) {
        return availableCars.get(i-1);
    }
}
