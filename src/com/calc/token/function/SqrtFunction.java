package com.calc.token.function;

class SqrtFunction extends FunctionToken.SingleArgumentFunction {
    @Override
    public Number calculate(Number... args) {
        return Math.sqrt(args[0].doubleValue());
    }
}
