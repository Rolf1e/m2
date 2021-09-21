package com.rolfie.patterns.flyweight.domain.dto;

import com.rolfie.patterns.flyweight.drawing.dto.Color;

public record Vehicle(String plate, VehicleProperties properties) {

    public Color getColor() {
        return properties.getColor();
    }
}
