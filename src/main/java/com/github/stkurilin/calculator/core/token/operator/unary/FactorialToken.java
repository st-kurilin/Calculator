package com.github.stkurilin.calculator.core.token.operator.unary;

import com.github.stkurilin.calculator.core.exception.CalculatorRuntimeException;

//Factorial defined only for nonnegative integers
public class FactorialToken extends UnaryToken {
    public FactorialToken() {
        super(true);
    }

    @Override
    public Number calculate(Number... args) {
        int a = provideNonnegativeInt(args[0]);
        int res = 1;
        for (int i = 1; i <= a; i++) {
            res *= i;
        }
        return res;
    }

    //return nonnegative int or throw CalculatorRuntimeException
    private int provideNonnegativeInt(Number n) {
        double val = n.doubleValue();
        if (Math.floor(val) != val || val < 0) {
            throw new CalculatorRuntimeException("Factorial defined only for nonnegative integers");
        }
        return n.intValue();
    }


}
