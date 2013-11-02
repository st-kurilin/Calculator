package com.calc.token;


public interface Token {
    void accept(TokenVisitor t);
}
