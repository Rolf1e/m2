package com.example.cacltigran.domain.service;

public class CalculatorServiceImpl implements CalculatorService {

    public static CalculatorService create() {
        return new CalculatorServiceImpl();
    }

    @Override
    public Number compute(int a, int b, Calculator operation) {
        return operation.compute(a, b);
    }
}
