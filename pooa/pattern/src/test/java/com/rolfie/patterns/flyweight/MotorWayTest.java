package com.rolfie.patterns.flyweight;

import com.rolfie.patterns.flyweight.domain.MotorWay;
import com.rolfie.patterns.flyweight.drawing.dto.Color;
import com.rolfie.patterns.flyweight.drawing.dto.Coordinate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Fly weight pattern implementation for course purposes
 * <p>
 * Inspired by this great work: {@link "https://refactoring.guru/design-patterns/flyweight"}
 */
public class MotorWayTest {

    @Test
    public void should_create_motor_way() {
        final var motorWay = MotorWay.create(2, 2);
        Assertions.assertEquals("WW\nWW\n", motorWay.getDrawing());
    }

    @Test
    public void test_vehicle_factory() {
        final var motorWay = MotorWay.create(2, 2);
        final var dolorean = motorWay.insertVehicle("OUTATIME", "DRC", Color.GREY, new Coordinate(0, 0));
        Assertions.assertEquals(dolorean.plate(), "OUTATIME");
        final var properties = dolorean.properties();
        Assertions.assertEquals(properties.getBrand(), "DRC");
        Assertions.assertEquals(properties.getColor(), Color.GREY);
    }

    @Test
    public void test_vehicle_factory_is_fly_weight() {
        final var motorWay = MotorWay.create(2, 2);
        final var dolorean = motorWay.insertVehicle("OUTATIME", "DRC", Color.GREY, new Coordinate(0, 0));
        final var dolorean2 = motorWay.insertVehicle("OUTATIME", "DRC", Color.GREY, new Coordinate(0, 0));

        // If both are equals, we used the same object for properties
        Assertions.assertTrue(dolorean.properties() == dolorean2.properties());
    }

    @Test
    public void should_create_motor_way_with_one_car() {
        final var motorWay = MotorWay.create(2, 2);
        motorWay.insertVehicle("OUTATIME", "DRC", Color.GREY, new Coordinate(0, 0));
        motorWay.insertVehicle("OUTATIME", "DRC", Color.GREY, new Coordinate(1, 0));
        Assertions.assertEquals("gg\nWW\n", motorWay.getDrawing());
    }

}