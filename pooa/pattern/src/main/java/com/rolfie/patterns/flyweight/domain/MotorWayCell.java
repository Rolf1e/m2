package com.rolfie.patterns.flyweight.domain;

import com.rolfie.patterns.flyweight.domain.dto.Vehicle;
import com.rolfie.patterns.flyweight.drawing.dto.Coordinate;

public record MotorWayCell(Vehicle vehicle, Coordinate coordinate) {
}
