package com.github.stkurilin.calculator.core.token.operator.unary;

public class IncrementToken extends UnaryToken {
    public IncrementToken() {
        super(false);
    }

    @Override
    public Number calculate(Number... args) {
        return args[0].doubleValue() + 1;
    }
}
