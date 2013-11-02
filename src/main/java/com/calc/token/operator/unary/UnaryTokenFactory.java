package com.calc.token.operator.unary;

import com.calc.tokenfactory.MapBasedTokenFactory;

public class UnaryTokenFactory extends MapBasedTokenFactory.ImmutableTokens {
    public UnaryTokenFactory() {
        addTokenInfo("!", new FactorialToken());
        addTokenInfo("++", new IncrementToken());
        addTokenInfo("--", new DecrementToken());
    }
}
