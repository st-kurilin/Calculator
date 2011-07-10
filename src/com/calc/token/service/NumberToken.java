package com.calc.token.service;

import com.calc.token.Token;
import com.calc.token.TokenVisitor;

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
