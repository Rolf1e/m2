package com.rolfie.patterns.flyweight.drawing.infra;

import com.rolfie.patterns.flyweight.domain.MotorWayCell;
import com.rolfie.patterns.flyweight.drawing.dto.Cell;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CanvasDrawer {

    private final int weight;
    private final int heigth;

    public static CanvasDrawer create(final int weight,
                                      final int heigth) {
        return new CanvasDrawer(weight, heigth);
    }

    public String draw(final List<MotorWayCell> vehicles) {
        final var canvas = Canvas.create(weight, heigth, vehicles);
        return Arrays.stream(canvas.getMatrix())
                .map(this::drawRow)
                .collect(Collectors.joining());
    }


    private String drawRow(final Cell[] row) {
        return Arrays.stream(row)
                .map(cell -> drawCell(Optional.ofNullable(cell).orElse(Cell.emptyCell())))
                .collect(Collectors.joining("", "", "\n"));
    }

    private String drawCell(final Cell cell) {
        return cell.toString();
    }

}
