package pl.tomek.rent;

import pl.tomek.car.Car;
import pl.tomek.user.User;

import java.math.BigDecimal;

public class Rent {
    private final User person;
    private final Car car;
    private final int numberOfDays;
    private final BigDecimal amount;

    public Rent(User person, Car car, int numberOfDays) {
        this.person = person;
        this.person.increaseNumberOfRentedCars();
        this.car = car;
        this.numberOfDays = numberOfDays;
        this.amount = car.getRentCost().multiply(BigDecimal.valueOf(numberOfDays));
    }

    public String showCars() {
        return person.getNameAndSureName() +
                " rents a car " +
                car.showBrandAndModel() +
                " for " + this.numberOfDays +
                " for a total cost of " +
                this.amount;
    }

}
