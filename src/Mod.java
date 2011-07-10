import com.calc.token.operator.binary.BinaryToken;

public class Mod extends BinaryToken {
    public Mod() {
        super(Order.MultiplicativeLevel);
    }

    @Override
    public Number calculate(Number... args) {
        int a = args[0].intValue();
        int b = args[1].intValue();
        return a % b;
    }
}