package pl.tomek.accounting;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class Account {
    private static final int MONTHS = 12;
    private final ArrayList<BigDecimal> earnings = new ArrayList<>();
    private final ArrayList<Integer> numberOfRentals = new ArrayList<>();

    public Account() {
        for (int i = 0; i < MONTHS; i++) {
            earnings.add(BigDecimal.ZERO);
            numberOfRentals.add(0);
        }
    }

    public void addAmount(BigDecimal bigDecimal)
    {
        int month = LocalDate.now().getMonthValue();
        BigDecimal monthSum = earnings.get(month - 1);
        addOneToNumberOfRentals(month);
        BigDecimal newSum = monthSum.add(bigDecimal);
        earnings.set(month-1,newSum);
    }

    private void addOneToNumberOfRentals(int month) {
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
                NumberFormat.getCurrencyInstance().format(earnings.get(index));
        System.out.println(sb);
    }

    public void yearReport()
    {
        System.out.println("Year " + LocalDate.now().getYear()+ " summary.");
        for (int i = 0; i < MONTHS; i++) {
            var sb = new StringBuilder("Earrings for ")
                    .append(Month.of(i + 1).name())
                    .append(". Total number of rentals: ")
                    .append(numberOfRentals.get(i))
                    .append(". Income:")
                    .append(NumberFormat.getCurrencyInstance().format(earnings.get(i)));
            System.out.println(sb);
        }
        System.out.println(sumYearMessage());
    }

    private String sumYearMessage()
    {
        var sb = new StringBuilder("Earrings for ")
                .append(LocalDate.now().getYear())
                .append(". Total numbers of rentals: ")
                .append(getSumOfRentals())
                .append(" Income: ")
                .append(NumberFormat.getCurrencyInstance().format(getYearSum()));
        return sb.toString();
    }

    @org.jetbrains.annotations.NotNull
    private Integer getSumOfRentals() {
        return numberOfRentals.stream()
                .reduce(Integer::sum)
                .orElse(-1);
    }

    @org.jetbrains.annotations.NotNull
    private BigDecimal getYearSum() {
        return earnings.stream()
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

}

