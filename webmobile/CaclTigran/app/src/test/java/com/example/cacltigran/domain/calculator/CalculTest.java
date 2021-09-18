package com.example.cacltigran.domain.calculator;

import com.example.cacltigran.domain.calulator.Calculator;
import com.example.cacltigran.domain.calulator.CalculatorService;
import com.example.cacltigran.domain.calulator.CalculatorServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculTest {

    private CalculatorService calculatorService;

    @Before
    public void init() {
        this.calculatorService = CalculatorServiceImpl.create();
    }

    @Test
    public void should_add_numbers() {
        Assert.assertEquals(3, calculatorService.compute(1, 2, Calculator.ADDITION));
        Assert.assertEquals(10, calculatorService.compute(5, 5, Calculator.ADDITION));
    }

    @Test
    public void should_subtract_numbers() {
        Assert.assertEquals(3, calculatorService.compute(7, 4, Calculator.SUBTRACTION));
        Assert.assertEquals(0, calculatorService.compute(5, 5, Calculator.SUBTRACTION));
    }

    @Test
    public void should_multiply_numbers() {
        Assert.assertEquals(28, calculatorService.compute(7, 4, Calculator.MULTIPLICATION));
        Assert.assertEquals(25, calculatorService.compute(5, 5, Calculator.MULTIPLICATION));
    }

    @Test
    public void should_divide_numbers() {
        Assert.assertEquals(3, calculatorService.compute(6, 2, Calculator.DIVISION));
        Assert.assertEquals(1, calculatorService.compute(5, 5, Calculator.DIVISION));
    }
}