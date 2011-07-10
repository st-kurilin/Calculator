package com.calc.token.function;

import com.calc.tokenfactory.MapBasedTokenFactory;

public class FunctionTokenFactory extends MapBasedTokenFactory.AutoSelectedTokens {
    public FunctionTokenFactory() {
        addTokenInfo("sqrt", new SqrtFunction());
        addTokenInfo("max", new MaxFunction());
        addTokenInfo("min", new MinFunction());
    }
}
