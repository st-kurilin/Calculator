package com.calc.token.operator.binary;

class MultiplicationOperator extends BinaryToken {
    public MultiplicationOperator() {
        super(Order.MultiplicativeLevel);
    }

    @Override
    public Number calculate(Number... args) {
        return args[0].doubleValue() * args[1].doubleValue();
    }

    @Override
    public String toString() {
        return "*";
    }
}
