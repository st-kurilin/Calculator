package com.github.stkurilin.calculator.core.token.service;

import com.github.stkurilin.calculator.core.token.Token;
import com.github.stkurilin.calculator.core.token.TokenVisitor;

public class OpeningParenthesis implements Token {

    public void accept(TokenVisitor t) {
        t.visitOpenParenthesis();
    }

    @Override
    public String toString() {
        return "(";
    }
}
