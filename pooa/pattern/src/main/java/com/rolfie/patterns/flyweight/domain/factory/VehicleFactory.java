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
public class VehicleFactory {

    private final List<VehicleProperties> existingProperties;

    public static VehicleFactory create() {
        return new VehicleFactory(new ArrayList<>());
    }

    public Vehicle create(final String plate,
                          final String brand,
                          final Color color) {
        final var props = findProperties(brand, color);
        if (props.isPresent()) {
            return new Vehicle(plate, props.get());
        }
        final var properties = VehicleProperties.create(brand, color);
        existingProperties.add(properties);
        return new Vehicle(plate, properties);
    }

    private Optional<VehicleProperties> findProperties(final String brand,
                                                       final Color color) {
        return existingProperties.stream()
                .filter(property -> brand.equals(property.getBrand()) && color.equals(property.getColor()))
                .findFirst();
    }


}
