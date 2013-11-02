package com.github.stkurilin.calculator.core.token.operator.binary;

import com.github.stkurilin.calculator.core.exception.CalculatorRuntimeException;
import com.github.stkurilin.calculator.core.token.operator.OperatorToken;

public abstract class BinaryToken extends OperatorToken {
    protected BinaryToken(Order order) {
        super(order);
    }

    public final void thereIsLeadingArguments(boolean leadingArguments) {
        if (!leadingArguments) {
            throw new CalculatorRuntimeException("Operator cannot be unary");
        }
    }

    public final int getNumberOfArguments() {
        return 2;
    }
}
