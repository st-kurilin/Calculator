package com.calc.token.operator;

import com.calc.token.ExecutableToken;
import com.calc.token.TokenVisitor;


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
