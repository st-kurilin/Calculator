package com.github.stkurilin.calculator.core.token.service;

import com.github.stkurilin.calculator.core.token.Token;
import com.github.stkurilin.calculator.core.token.TokenVisitor;

public class NumberToken implements Token {
    private Double value;

    public NumberToken(Double value) {
        this.value = value;
    }

    public NumberToken(Number value) {
        this(value.doubleValue());
    }

    public Double getValue() {
        return value;
    }

    public void accept(TokenVisitor t) {
        t.visitNumber(this);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
