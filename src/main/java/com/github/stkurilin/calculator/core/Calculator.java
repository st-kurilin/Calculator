package com.github.stkurilin.calculator.core;

import com.github.stkurilin.calculator.core.exception.CalculatorException;
import com.github.stkurilin.calculator.core.exception.CalculatorRuntimeException;
import com.github.stkurilin.calculator.core.lexicalanalysis.Tokenizer;
import com.github.stkurilin.calculator.core.token.CompositeTokenFactory;
import com.github.stkurilin.calculator.core.token.TokenFactory;
import com.github.stkurilin.calculator.core.token.function.FunctionTokenFactory;
import com.github.stkurilin.calculator.core.token.operator.additive.AdditiveTokenFactory;
import com.github.stkurilin.calculator.core.token.operator.binary.BinaryTokenFactory;
import com.github.stkurilin.calculator.core.token.operator.unary.UnaryTokenFactory;
import com.github.stkurilin.calculator.core.token.service.ServiceTokenFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.github.stkurilin.calculator.core.token.TokenVisitor.Utils.process;

/**
 * Calculates expression from given string.
 */
public class Calculator {
    private final Logger logger = LoggerFactory.getLogger(Calculator.class);
    private final Tokenizer tokenizer;
    private final CompositeTokenFactory factories;

    public Calculator() {
        factories = new CompositeTokenFactory(
                new AdditiveTokenFactory(), new BinaryTokenFactory(), new ServiceTokenFactory(),
                new FunctionTokenFactory(), new UnaryTokenFactory());
        tokenizer = new Tokenizer(factories);
    }

    public Number calc(CharSequence inp) throws CalculatorException {
        logger.debug("calculate input: {}", inp);
        try {
            return process(new CalcPolishAnnotationVisitor(),
                    process(new ToPolishAnnotationVisitor(), tokenizer.apply(inp)));
        } catch (CalculatorRuntimeException e) {
            CalculatorException ee = new CalculatorException(e);
            ee.initCause(e);
            throw ee;
        }
    }

    public void addAlias(String oldName, String newName) {
        factories.addAlias(oldName, newName);
    }

    public void addTokenFactory(TokenFactory factory) {
        factories.addTokenFactory(factory);
    }
}

