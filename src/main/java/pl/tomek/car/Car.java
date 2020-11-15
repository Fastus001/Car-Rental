package pl.tomek.car;

import pl.tomek.main.Validate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class Car {
    private final String brand;
    private final String model;
    private final int mileage;
    private final LocalDate yearOfProduction;
    private BigDecimal rent;

    public void setRent(double rent) {
        if(rent>0)
             this.rent = BigDecimal.valueOf(rent);
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
                ", rent = " + rent;
    }

    public String showBrandAndModel()
    {
        return brand + " " + model;
    }

    public BigDecimal getRentCost() {
        return rent;
    }

    public static Car makeNewCar()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Specify brand: ");
        String brand = in.next();

        System.out.println("Specify model: ");
        String model = in.next();

        System.out.println("Specify mileage: ");
        int mileage = in.nextInt();

        System.out.println("Specify year of production(in format yyyy-mm-dd): ");
        String data = in.next();
        if(Validate.validateDataFormat(data))
        {
            String[] date = data.split("-");
            int year = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int day = Integer.parseInt(date[2]);
            System.out.println("Specify price per day: ");
            double rent = in.nextDouble();
            return new Car(brand,model,mileage,year,month,day,rent);
        }else
            System.out.println("Date format not correct, adding new car aborted!");
        return null;
    }

}
