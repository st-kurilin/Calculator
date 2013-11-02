package com.calc.token.operator.additive;

public class PlusOperator extends AdditiveToken {
    @Override
    public Number calculate(Number... args) {
        if (isUnary()) {
            return args[0].doubleValue();
        } else {
            return args[0].doubleValue() + args[1].doubleValue();
        }
    }

    @Override
    public String toString() {
        return "+";
    }
}