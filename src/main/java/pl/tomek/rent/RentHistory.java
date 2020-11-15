package pl.tomek.rent;

import java.util.ArrayList;

public class RentHistory {
    private ArrayList<Rent> rentHistory = new ArrayList<>();

    public void addRent(Rent rent)
    {
        this.rentHistory.add(rent);
    }

    public void showAllRentedCars()
    {
        System.out.println("Active rentals:\n");
        for (int i = 0; i < rentHistory.size(); i++) {
                System.out.println("Id: "+Integer.toString(i+1)+": " + rentHistory.get(i).showCars());
        }
    }
}
