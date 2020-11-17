package pl.tomek.main;

import pl.tomek.car.Car;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Validate {

    public static boolean validatePesel(String pesel)
    {
        return Pattern.compile("\\d{11}").matcher(pesel).matches();
    }

    public static boolean validateDataFormat(String data)
    {
        return Pattern.compile("\\d{4}-\\d{2}-\\d{2}").matcher(data).matches();
    }

    public static Car validateNewCar()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Specify brand: ");
        String brand = in.nextLine();

        System.out.println("Specify model: ");
        in.next();
        String model = in.nextLine();

        System.out.println("Specify mileage: ");
//        in.next();
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

    public static LocalDate parsePeselID(String input)
    {
        int year = Integer.parseInt(input.substring(0,2));
        int month = Integer.parseInt(input.substring(2,4));
        if(month>20){
            year += 2000;
            month -= 20;
        }
        else
            year += 1900;
        int day = Integer.parseInt(input.substring(4,6));
        return LocalDate.of(year,month,day);
    }

}
