package com.github.stkurilin.calculator.core.token.operator.binary;

import com.github.stkurilin.calculator.core.token.MapBasedTokenFactory;

public class BinaryTokenFactory extends MapBasedTokenFactory.ImmutableTokens {
    public BinaryTokenFactory() {
        addTokenInfo("*", new MultiplicationOperator());
        addTokenInfo("/", new DivisionOperator());
        addTokenInfo("^", new PowerOperator());
    }
}
