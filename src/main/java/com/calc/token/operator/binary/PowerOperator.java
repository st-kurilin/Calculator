package com.calc.token.operator.binary;

class PowerOperator extends BinaryToken {
    public PowerOperator() {
        super(Order.PowerLevel);
    }

    @Override
    public Number calculate(Number... args) {
        double a = args[0].doubleValue();
        double b = args[1].doubleValue();
        return Math.pow(a, b);
    }

    protected boolean isLeftAssociative() {
        return false;
    }

    @Override
    public String toString() {
        return "^";
    }
}
