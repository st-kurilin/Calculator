package com.github.stkurilin.calculator.core.token;

public interface MutableToken extends Token, Cloneable {
    Token clone() throws CloneNotSupportedException;
}
