package pl.tomek.cars;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {
    @Test
    public void testClasCarToString()
    {
        Car car = new Car("Opel","Astra", 125000,1990,12,12,23);
        System.out.println("car = " + car);
    }

    @Test
    public void testSetRentCar()
    {
        Car car = new Car("Opel","Astra", 125000,1990,12,12,23);
        assertEquals(0,car.getRentCost().compareTo(BigDecimal.valueOf(23)));
        car.setRent(45.60);
        assertEquals(0,car.getRentCost().compareTo(BigDecimal.valueOf(45.60)));
        assertNotEquals(0,car.getRentCost().compareTo(BigDecimal.valueOf(45.61)));
    }

}