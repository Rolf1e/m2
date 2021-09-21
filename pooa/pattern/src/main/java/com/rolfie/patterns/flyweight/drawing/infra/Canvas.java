package com.rolfie.patterns.flyweight.drawing.infra;

import com.rolfie.patterns.flyweight.domain.MotorWayCell;
import com.rolfie.patterns.flyweight.drawing.dto.Cell;
import com.rolfie.patterns.flyweight.drawing.dto.Coordinate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
class Canvas {

    @Getter
    private final Cell[][] matrix;

    public static Canvas create(final int weight,
                                final int heigth,
                                final List<MotorWayCell> motorWayCells) {

        final var canvas = new Canvas(new Cell[weight][heigth]);
        for (final var cell : motorWayCells) {
            canvas.add(Cell.create(cell.vehicle().getColor()), cell.coordinate());
        }

        return canvas;
    }

    private void add(final Cell cell, final Coordinate coordinate) {
        matrix[coordinate.y()][coordinate.x()] = cell;
    }


}
