package com.github.stkurilin.calculator.core.token.operator.unary;

import com.github.stkurilin.calculator.core.token.MapBasedTokenFactory;

public class UnaryTokenFactory extends MapBasedTokenFactory.ImmutableTokens {
    public UnaryTokenFactory() {
        addTokenInfo("!", new FactorialToken());
        addTokenInfo("++", new IncrementToken());
        addTokenInfo("--", new DecrementToken());
    }
}
