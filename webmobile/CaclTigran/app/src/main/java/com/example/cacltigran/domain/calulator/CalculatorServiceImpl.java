package com.example.cacltigran.domain.calulator;

public class CalculatorServiceImpl implements CalculatorService {

    public static CalculatorService create() {
        return new CalculatorServiceImpl();
    }

    @Override
    public Number compute(final int a, final int b, final Calculator operation) {
        return operation.compute(a, b);
    }
}
