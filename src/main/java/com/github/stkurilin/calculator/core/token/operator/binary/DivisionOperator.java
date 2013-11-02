package com.github.stkurilin.calculator.core.token.operator.binary;

class DivisionOperator extends BinaryToken {
    public DivisionOperator() {
        super(Order.MultiplicativeLevel);
    }

    @Override
    public Number calculate(Number... args) {
        return args[0].doubleValue() / args[1].doubleValue();
    }

    @Override
    public String toString() {
        return "/";
    }
}
