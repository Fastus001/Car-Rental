package pl.tomek.accounting;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class Account {

    private final ArrayList<BigDecimal> earnings = new ArrayList<>();
    private final ArrayList<Integer> numberOfRentals = new ArrayList<>();
    public Account() {

        for (int i = 0; i < 12; i++) {
            earnings.add(BigDecimal.ZERO);
            numberOfRentals.add(0);
        }
    }

    public void addAmount(BigDecimal bigDecimal)
    {
        int month = LocalDate.now().getMonthValue();
        BigDecimal monthSum = earnings.get(month - 1);
        addOneToNumbrOfRentals(month);
        BigDecimal newSum = monthSum.add(bigDecimal);
        earnings.set(month-1,newSum);
    }

    private void addOneToNumbrOfRentals(int month) {
        Integer nOfRentals = numberOfRentals.get(month -1);
        nOfRentals += 1;
        numberOfRentals.set(month -1, nOfRentals);
    }

    public void monthReport()
    {
        LocalDate date = LocalDate.now();
        int index = date.getMonthValue()-1;

        var sb = "Earrings for " +
                date.getMonth().name() +
                ". Total number of rentals: " +
                numberOfRentals.get(index) + ". Income:" +
                earnings.get(index);
        System.out.println(sb);
    }

    public void yearReport()
    {
        System.out.println("Year " + LocalDate.now().getYear()+ " summary.");
        for (int i = 0; i < 12; i++) {
            var sb = "Earrings for " +
                    Month.of(i + 1).name() +
                    ". Total number of rentals: " +
                    numberOfRentals.get(i) + ". Income:" +
                    earnings.get(i);
            System.out.println(sb);
        }
        System.out.println(sumYearMessaage());
    }

    private String sumYearMessaage()
    {
        Double yearSum = getYearSum();
        Integer sumOfRentals = getSumOfRentals();
        String sb = "Earrings for " + LocalDate.now().getYear() +
                ". Total numbers of rentals: " +
                sumOfRentals +
                " Income: " +
                yearSum;
        return sb;
    }

    @org.jetbrains.annotations.NotNull
    private Integer getSumOfRentals() {
        return numberOfRentals.stream()
                .reduce(Integer::sum)
                .orElse(-1);
    }

    @org.jetbrains.annotations.NotNull
    private Double getYearSum() {
        return earnings.stream()
                .map(BigDecimal::doubleValue)
                .reduce(Double::sum)
                .orElse(-1.0);
    }

}

