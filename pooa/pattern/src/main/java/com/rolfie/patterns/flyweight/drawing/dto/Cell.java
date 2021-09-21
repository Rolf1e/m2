package com.rolfie.patterns.flyweight.drawing.dto;

public record Cell(Color color) {

    private static final Cell EMPTY_CELL = new Cell(Color.WHITE);

    public static Cell emptyCell() {
        return EMPTY_CELL;
    }

    public static Cell create(final Color color) {
        return new Cell(color);
    }

    @Override
    public String toString() {
        return color.toString();
    }
}
