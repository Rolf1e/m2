package com.rolfie.patterns.flyweight.domain;

import com.rolfie.patterns.flyweight.domain.dto.Vehicle;
import com.rolfie.patterns.flyweight.domain.factory.VehicleFactory;
import com.rolfie.patterns.flyweight.drawing.dto.Color;
import com.rolfie.patterns.flyweight.drawing.dto.Coordinate;
import com.rolfie.patterns.flyweight.drawing.infra.CanvasDrawer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class MotorWay {

    private final List<MotorWayCell> vehicles;
    private final CanvasDrawer drawer;
    private final VehicleFactory factory;

    public static MotorWay create(final int weight,
                                  final int heigth) {

        return new MotorWay(new ArrayList<>(), CanvasDrawer.create(weight, heigth), VehicleFactory.create());
    }

    public Vehicle insertVehicle(final String plate,
                                 final String brand,
                                 final Color color,
                                 final Coordinate coordinate) {

        final var vehicle = factory.newVehicle(plate, brand, color);
        vehicles.add(new MotorWayCell(vehicle, coordinate));
        return vehicle;
    }

    public String getDrawing() {
        return drawer.draw(vehicles);
    }

}
