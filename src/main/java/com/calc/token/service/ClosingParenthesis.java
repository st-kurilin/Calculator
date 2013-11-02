package com.calc.token.service;

import com.calc.token.Token;
import com.calc.token.TokenVisitor;

public class ClosingParenthesis implements Token {

    public void accept(TokenVisitor t) {
        t.visitCloseParenthesis();
    }
}