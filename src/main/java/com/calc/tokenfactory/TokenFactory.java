package com.calc.tokenfactory;

import com.calc.token.Token;

import java.text.ParsePosition;

public interface TokenFactory {
    /*
    * Returns a token if possible, or null otherway.
    * Does not throw an exception; if no object can be parsed, index is
    * unchanged!
    */
    Token createToken(String source, ParsePosition parsePosition);

    /*add aliases for existing old lexeme*/
    /*if old lexeme existing in this factory return true*/
    boolean addAlias(String old, String... aliases);
}
