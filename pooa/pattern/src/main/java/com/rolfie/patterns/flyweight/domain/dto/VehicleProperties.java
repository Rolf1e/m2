package com.rolfie.patterns.flyweight.domain.dto;

import com.rolfie.patterns.flyweight.drawing.dto.Color;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class VehicleProperties {

    private final String brand;
    private final Color color;

    public static VehicleProperties create(final String brand,
                                           final Color color) {
        return new VehicleProperties(brand, color);
    }

}
