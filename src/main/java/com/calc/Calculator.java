package com.calc;

import com.calc.exception.CalculatorException;
import com.calc.exception.CalculatorRuntimeException;
import com.calc.lexicalanalysis.Tokenizer;
import com.calc.token.Token;
import com.calc.token.function.FunctionTokenFactory;
import com.calc.token.operator.additive.AdditiveTokenFactory;
import com.calc.token.operator.binary.BinaryTokenFactory;
import com.calc.token.operator.unary.UnaryTokenFactory;
import com.calc.token.service.ServiceTokenFactory;
import com.calc.tokenfactory.CompositeTokenFactory;
import com.calc.tokenfactory.TokenFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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
            return calcFromPolishAnnotation(toPolishAnnotation(tokenizer.apply(inp)));
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

    private Number calcFromPolishAnnotation(List<Token> tokens) {
        logger.debug("in Reverse Polish notation: {}", tokens);
        final CalcPolishAnnotationVisitor visitor = new CalcPolishAnnotationVisitor();
        for (Token t : tokens) {
            t.accept(visitor);
        }
        final Number res = visitor.getResult();
        logger.debug("result: {}", res);
        return res;
    }

    private List<Token> toPolishAnnotation(Iterable<Token> tokens) {
        final ToPolishAnnotationVisitor visitor = new ToPolishAnnotationVisitor();
        for (Token each : tokens) {
            each.accept(visitor);
        }
        return visitor.inPolishAnnotation();
    }
}

