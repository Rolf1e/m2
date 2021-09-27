package com.rolfie.patterns.flyweight;

import com.rolfie.patterns.flyweight.domain.MotorWayFlyweight;
import com.rolfie.patterns.flyweight.domain.MotorWay;
import com.rolfie.patterns.flyweight.domain.dto.Vehicle;
import com.rolfie.patterns.flyweight.drawing.dto.Color;
import com.rolfie.patterns.flyweight.drawing.dto.Coordinate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Fly weight pattern implementation for course purposes
 * <p>
 * Inspired by this great work: {@link "https://refactoring.guru/design-patterns/flyweight"}
 */
public class MotorWayTest {

    @Test
    public void should_create_motor_way() {
        final var motorWay = MotorWayFlyweight.create(2, 2);
        Assertions.assertEquals("WW\nWW\n", motorWay.getDrawing());
    }

    @Test
    public void test_vehicle_factory() {
        final var motorWay = MotorWayFlyweight.create(2, 2);
        final var dolorean = motorWay.insertVehicle("OUTATIME", "DRC", Color.GREY, new Coordinate(0, 0));
        Assertions.assertEquals(dolorean.plate(), "OUTATIME");
        final var properties = dolorean.properties();
        Assertions.assertEquals(properties.getBrand(), "DRC");
        Assertions.assertEquals(properties.getColor(), Color.GREY);
    }

    @Test
    public void test_vehicle_factory_is_fly_weight() {
        final var motorWay = MotorWayFlyweight.create(2, 2);
        final var dolorean = motorWay.insertVehicle("OUTATIME", "DRC", Color.GREY, new Coordinate(0, 0));
        final var dolorean2 = motorWay.insertVehicle("OUTATIME", "DRC", Color.GREY, new Coordinate(0, 0));

        // If both are equals, we used the same object for properties
        Assertions.assertTrue(dolorean.properties() == dolorean2.properties());
    }

    @Test
    public void should_create_motor_way_with_one_car() {
        final var motorWay = MotorWayFlyweight.create(2, 2);
        motorWay.insertVehicle("OUTATIME", "DRC", Color.GREY, new Coordinate(0, 0));
        motorWay.insertVehicle("OUTATIME", "DRC", Color.GREY, new Coordinate(1, 0));
        Assertions.assertEquals("gg\nWW\n", motorWay.getDrawing());
    }



    private String getRandomString(){

        String[] texts = {"one","two", "three", "four", "five", "six", "seven", "height", "nine", "ten"};
        return texts[(int) (Math.random() * 10)];

    }


    @Test
    public void test_vehicle_without_flyweight_factory() {

        int numberIteration = 10000000;
        double currentMemory;

        //***************
        // Without Flyweight
        //***************

        long start = System.nanoTime();
        currentMemory = ( (double)((double)(Runtime.getRuntime().totalMemory()/1024)/1024))- ((double)((double)(Runtime.getRuntime().freeMemory()/1024)/1024));
        System.out.println(currentMemory);

        final var motorWay = MotorWay.create(2, 2);

        List<Vehicle> vehicles = new ArrayList<>();
        for (int i = 0; i < numberIteration; i++) {
            vehicles.add(motorWay.insertVehicle(this.getRandomString(), this.getRandomString(), Color.GREY, new Coordinate(0, 0)));
        }

        currentMemory = ( (double)((double)(Runtime.getRuntime().totalMemory()/1024)/1024))- ((double)((double)(Runtime.getRuntime().freeMemory()/1024)/1024));
        System.out.println(currentMemory);

        long elapsedTimeWithoutFlyweight = (System.nanoTime() - start) / 1000000;

        //****************
        // With Flyweight
        //**************

        start = System.nanoTime();
        final var motorWayWithFlyweight = MotorWayFlyweight.create(2, 2);

        List<Vehicle> vehicles2 = new ArrayList<>();

        for (int i = 0; i < numberIteration; i++) {
            vehicles2.add(motorWayWithFlyweight.insertVehicle(this.getRandomString(),this.getRandomString(), Color.GREY, new Coordinate(0, 0)));
        }

        long elapsedTimeWithFlyweight = (System.nanoTime() - start) / 1000000;

        currentMemory = ( (double)((double)(Runtime.getRuntime().totalMemory()/1024)/1024))- ((double)((double)(Runtime.getRuntime().freeMemory()/1024)/1024));
        System.out.println(currentMemory);

        System.out.println("without flyweight => " + elapsedTimeWithoutFlyweight + " ms");
        System.out.println("with flyweight    => " + elapsedTimeWithFlyweight +" ms");

        Assertions.assertEquals(true,true);

    }

}