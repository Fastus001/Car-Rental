package pl.tomek.rent;

import pl.tomek.cars.Car;
import pl.tomek.users.User;

import java.math.BigDecimal;

public class Rent {
    private User person;
    private Car car;
    private int numberOfDays;
    private BigDecimal amount;

    public Rent(User person, Car car, int numberOfDays) {
        this.person = person;
        this.person.increasseNumberOfRentedCars();
        this.car = car;
        this.numberOfDays = numberOfDays;
        this.amount = car.getRentCost().multiply(BigDecimal.valueOf(numberOfDays));
    }

    public String showCars() {
        return this.car.showCar();
    }
}
