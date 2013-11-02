package com.calc.token.service;

import com.calc.token.Token;
import com.calc.token.TokenVisitor;

public class OpeningParenthesis implements Token {

    public void accept(TokenVisitor t) {
        t.visitOpenParenthesis();
    }

    @Override
    public String toString() {
        return "(";
    }
}
