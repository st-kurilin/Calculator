package com.calc.token.operator.additive;

import com.calc.token.MutableToken;
import com.calc.token.operator.OperatorToken;

public abstract class AdditiveToken extends OperatorToken implements MutableToken {

    public int getNumberOfArguments() {
        return isUnary() ? 1 : 2;
    }

    @Override
    public void thereIsLeadingArguments(boolean leadingArguments) {
        order = !leadingArguments ? Order.UnaryOperator : Order.AdditiveOperator;
    }

    @Override
    public AdditiveToken clone() throws CloneNotSupportedException {
        return (AdditiveToken) super.clone();
    }

    public boolean isUnary() {
        return order == Order.UnaryOperator;
    }
}
