package pl.tomek.car;

import pl.tomek.main.Validate;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Scanner;

public class Car {
    private final String brand;
    private final String model;
    private final int mileage;
    private final LocalDate yearOfProduction;
    private BigDecimal rent;

    public void setRent(BigDecimal rent) {
        if(rent.compareTo(BigDecimal.ZERO)>0)
             this.rent = rent;
        else
            System.out.println("Price you give is lover then 0, rent price have to be above 0!");
    }

    public Car(String brand, String model, int mileage, int year, int month, int day, double rent) {
        this.brand = brand;
        this.model = model;
        this.mileage = mileage;
        this.yearOfProduction = LocalDate.of(year,month,day);
        this.rent = BigDecimal.valueOf(rent);
    }

    public String showCar() {
        return brand + "-" + model +
                ", mileage = " + mileage +
                ", year of production = " + yearOfProduction +
                ", rent = " + currencyFormat(rent);
    }

    public static String currencyFormat(BigDecimal bd)
    {
        return NumberFormat.getCurrencyInstance().format(bd);
    }

    public String showBrandAndModel()
    {
        return brand + " " + model;
    }

    public BigDecimal getRentCost() {
        return rent;
    }

}
