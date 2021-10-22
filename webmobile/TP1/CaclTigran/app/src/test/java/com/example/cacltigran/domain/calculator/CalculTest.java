package com.example.cacltigran.domain.calculator;

import org.junit.Assert;
import org.junit.Test;

public class CalculTest {

    @Test
    public void should_add_numbers() {
        Assert.assertEquals(3, Calculator.ADDITION.compute(1, 2));
        Assert.assertEquals(10, Calculator.ADDITION.compute(5, 5));
    }

    @Test
    public void should_subtract_numbers() {
        Assert.assertEquals(3, Calculator.SUBTRACTION.compute(7, 4));
        Assert.assertEquals(0, Calculator.SUBTRACTION.compute(5, 5));
    }

    @Test
    public void should_multiply_numbers() {
        Assert.assertEquals(28, Calculator.MULTIPLICATION.compute(7, 4));
        Assert.assertEquals(25, Calculator.MULTIPLICATION.compute(5, 5));
    }

    @Test
    public void should_divide_numbers() {
        Assert.assertEquals(3, Calculator.DIVISION.compute(6, 2));
        Assert.assertEquals(1, Calculator.DIVISION.compute(5, 5));
    }
}