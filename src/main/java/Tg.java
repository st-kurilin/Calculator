import com.calc.token.function.FunctionToken;

public class Tg extends FunctionToken.SingleArgumentFunction {
    @Override
    public Number calculate(Number... numbers) {
        double arg = numbers[0].doubleValue();
        double res = Math.tan(Math.toRadians(arg));
        return Math.round(res * 100) / 100.;
    }
}