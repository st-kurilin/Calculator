package com.calc.token;

public interface MutableToken extends Token, Cloneable {
    Token clone() throws CloneNotSupportedException;
}
