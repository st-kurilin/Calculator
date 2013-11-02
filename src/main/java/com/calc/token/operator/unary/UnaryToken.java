package com.calc.token.operator.unary;

import com.calc.exception.CalculatorRuntimeException;
import com.calc.token.operator.OperatorToken;

public abstract class UnaryToken extends OperatorToken {
    //is unary operator placed after operand, like factorial or before it, like unary plus
    private boolean placedAfterOperand;

    public UnaryToken(boolean placedAfterOperand) {
        super(Order.UnaryOperator);
        this.placedAfterOperand = placedAfterOperand;
    }

    public final boolean isPlacedAfterOperand() {
        return placedAfterOperand;
    }

    public final boolean isPlacedBeforeOperand() {
        return !placedAfterOperand;
    }

    public final int getNumberOfArguments() {
        return 1;
    }

    @Override
    public final void thereIsLeadingArguments(boolean leadingArguments) {
        if (leadingArguments ^ isPlacedAfterOperand()) {
            throw new CalculatorRuntimeException("Illegal operator position");
        }
    }
}
