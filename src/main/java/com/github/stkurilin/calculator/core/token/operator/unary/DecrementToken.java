package com.github.stkurilin.calculator.core.token.operator.unary;

public class DecrementToken extends UnaryToken {
    public DecrementToken() {
        super(false);
    }

    @Override
    public Number calculate(Number... args) {
        return args[0].doubleValue() - 1;
    }
}