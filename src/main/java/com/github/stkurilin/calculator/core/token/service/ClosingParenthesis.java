package com.github.stkurilin.calculator.core.token.service;

import com.github.stkurilin.calculator.core.token.Token;
import com.github.stkurilin.calculator.core.token.TokenVisitor;

public class ClosingParenthesis implements Token {

    public void accept(TokenVisitor t) {
        t.visitCloseParenthesis();
    }
}