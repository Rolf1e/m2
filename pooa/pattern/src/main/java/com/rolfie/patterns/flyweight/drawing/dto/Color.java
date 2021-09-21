package com.rolfie.patterns.flyweight.drawing.dto;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Color {
    RED("R"),
    GREEN("G"),
    BLUE("B"),
    GREY("g"),
    WHITE("W");


    private final String color;

    @Override
    public String toString() {
        return color;
    }
}
