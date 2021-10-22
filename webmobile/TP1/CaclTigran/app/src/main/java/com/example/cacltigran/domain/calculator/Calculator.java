package com.example.cacltigran.domain.calculator;

enum Calculator {
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

    public static Calculator from(final String operation) {
        switch (operation) {
            case "Ajouter":
                return ADDITION;
            case "Soustraire":
                return SUBTRACTION;
            case "Multiplier":
                return MULTIPLICATION;
            case "Diviser":
                return DIVISION;
            default:
                throw new IllegalStateException("Illegal operation name: " + operation);
        }

    }
}
