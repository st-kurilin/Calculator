package com.github.stkurilin.calculator;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArithmeticTest.class, ValidationTests.class, FunctionTests.class, AliasesTests.class, ExtraTokenTests.class
})
public class TestsRunner {
}
