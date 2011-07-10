package com.calc;

import com.calc.exception.CalculatorException;
import com.calc.exception.CalculatorRuntimeException;
import com.calc.lexicalanalysis.Analyzer;
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


public class Calculator {
    private Analyzer analyzer;
    private Logger logger = LoggerFactory.getLogger(Calculator.class);
    private CompositeTokenFactory factories;


    public Calculator() {
        factories = new CompositeTokenFactory(
                new AdditiveTokenFactory(), new BinaryTokenFactory(), new ServiceTokenFactory(),
                new FunctionTokenFactory(), new UnaryTokenFactory());
        analyzer = new Analyzer(factories);
    }

    public Number calc(CharSequence inp) throws CalculatorException {
        logger.debug("calculate input: {}", inp);
        try {
            return calcFromPolishAnnotation(toPolishAnnotation(analyzer.getIterable(inp)));
        } catch (CalculatorRuntimeException e) {
            RuntimeException ee =  new RuntimeException("error>" + inp);
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
        CalcPolishAnnotationVisitor visitor = new CalcPolishAnnotationVisitor();
        for (Token t : tokens) {
            t.accept(visitor);
        }
        Number res = visitor.getResult();
        logger.debug("result: {}", res);
        return res;
    }

    private List<Token> toPolishAnnotation(Iterable<Token> tokens) {
        ToPolishAnnotationVisitor visitor = new ToPolishAnnotationVisitor();
        for (Token t : tokens) {
            t.accept(visitor);
        }
        return visitor.inPolishAnnotation();
    }

}

