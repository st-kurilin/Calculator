package com.github.stkurilin.calculator.core.token.operator;

import com.github.stkurilin.calculator.core.token.ExecutableToken;
import com.github.stkurilin.calculator.core.token.TokenVisitor;


public abstract class OperatorToken extends ExecutableToken {
    protected OperatorToken(Order order) {
        super(order);
    }

    protected OperatorToken() {
    }

    public void accept(TokenVisitor t) {
        t.visitOperator(this);
    }

    public abstract void thereIsLeadingArguments(boolean leadingArguments);
}
