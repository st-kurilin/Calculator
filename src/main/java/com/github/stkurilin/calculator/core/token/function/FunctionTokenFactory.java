package com.github.stkurilin.calculator.core.token.function;

import com.github.stkurilin.calculator.core.token.MapBasedTokenFactory;

public class FunctionTokenFactory extends MapBasedTokenFactory.AutoSelectedTokens {
    public FunctionTokenFactory() {
        addTokenInfo("sqrt", new SqrtFunction());
        addTokenInfo("max", new MaxFunction());
        addTokenInfo("min", new MinFunction());
    }
}
