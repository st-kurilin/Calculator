package com.github.stkurilin.calculator.core.token.function;

class MinFunction extends FunctionToken.MultiArgumentsFunction {
    @Override
    public boolean hasLegalNumberOfArguments() {
        return getNumberOfArguments() >= 2;
    }

    @Override
    public Number calculate(Number... args) {
        Double min = Double.MAX_VALUE;
        for (Number n : args) {
            min = Math.min(min, n.doubleValue());
        }
        return min;
    }
}
