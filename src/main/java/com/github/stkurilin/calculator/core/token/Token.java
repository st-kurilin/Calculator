package com.github.stkurilin.calculator.core.token;


public interface Token {
    void accept(TokenVisitor t);
}
