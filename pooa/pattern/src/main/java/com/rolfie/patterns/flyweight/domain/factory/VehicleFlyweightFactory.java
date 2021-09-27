package com.rolfie.patterns.flyweight.domain.factory;

import com.rolfie.patterns.flyweight.domain.dto.Vehicle;
import com.rolfie.patterns.flyweight.domain.dto.VehicleProperties;
import com.rolfie.patterns.flyweight.drawing.dto.Color;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This factory is special, as she uses the same instances
 * for vehicles properties whenever it's possible
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class VehicleFlyweightFactory {

    private final List<VehicleProperties> existingProperties;
    private final VehicleFactory factory;

    public static VehicleFlyweightFactory create() {
        return new VehicleFlyweightFactory(new ArrayList<>(), VehicleFactory.create());
    }

    public Vehicle newVehicle(final String plate,
                              final String brand,
                              final Color color) {
        final var props = findProperties(brand, color);
        if (props.isPresent()) {
            return factory.newVehicleFromProperties(plate, props.get());
        }

        final var vehicle = factory.newVehicle(plate, brand, color);
        existingProperties.add(vehicle.properties());
        return vehicle;
    }

    private Optional<VehicleProperties> findProperties(final String brand,
                                                       final Color color) {
        return existingProperties.stream()
                .filter(property -> isSameProperties(property, brand, color))
                .findFirst();
    }

    private boolean isSameProperties(final VehicleProperties properties,
                                     final String brand,
                                     final Color color) {
        return brand.equals(properties.getBrand()) && color.equals(properties.getColor());
    }


}
