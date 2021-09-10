package com.example.cacltigran.domain.service;

public enum Calculator {
    ADDITION {
        @Override
        public Number compute(int a, int b) {
            return a + b;
        }
    },
    SUBTRACTION {
        @Override
        public Number compute(int a, int b) {
            return a - b;
        }
    },
    MULTIPLICATION {
        @Override
        public Number compute(int a, int b) {
            return a * b;
        }
    },
    DIVISION {
        @Override
        public Number compute(int a, int b) {
            return a / b;
        }
    };

    abstract Number compute(final int a, final int b);
}
