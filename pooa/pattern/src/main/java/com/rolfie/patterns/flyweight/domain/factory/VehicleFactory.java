package com.rolfie.patterns.flyweight.domain.factory;

import com.rolfie.patterns.flyweight.domain.dto.Vehicle;
import com.rolfie.patterns.flyweight.domain.dto.VehicleProperties;
import com.rolfie.patterns.flyweight.drawing.dto.Color;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class VehicleFactory {

    public static VehicleFactory create() {
        return new VehicleFactory();
    }

    public Vehicle create(final String plate, final String brand, final Color color) {
        return new Vehicle(plate, VehicleProperties.create(brand, color));
    }

}
