package com.calc.token.operator.binary;

import com.calc.tokenfactory.MapBasedTokenFactory;

public class BinaryTokenFactory extends MapBasedTokenFactory.ImmutableTokens {
    public BinaryTokenFactory() {
        addTokenInfo("*", new MultiplicationOperator());
        addTokenInfo("/", new DivisionOperator());
        addTokenInfo("^", new PowerOperator());
    }
}
