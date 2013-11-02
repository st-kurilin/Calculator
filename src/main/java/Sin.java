import com.github.stkurilin.calculator.core.token.function.FunctionToken;

public class Sin extends FunctionToken.SingleArgumentFunction {

    @Override
    public Number calculate(Number... numbers) {
        double arg = numbers[0].doubleValue();
        double res = Math.sin(Math.toRadians(arg));
        return Math.round(res * 100) / 100.;
    }
}
