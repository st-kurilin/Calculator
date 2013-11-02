package com.calc.token.function;

class MaxFunction extends FunctionToken.MultiArgumentsFunction {
    @Override
    public boolean hasLegalNumberOfArguments() {
        return getNumberOfArguments() >= 2;
    }

    @Override
    public Number calculate(Number... args) {
        Double max = Double.MIN_VALUE;
        for (Number n : args) {
            max = Math.max(max, n.doubleValue());
        }
        return max;
    }

}
