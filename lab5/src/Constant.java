import java.text.*;
import java.util.Locale;

public class Constant extends Node {
    double value;

    Constant(double value)
    {
        this.sign = value<0 ? -1 : 1;
        this.value = value<0 ? -value : value;
    }

    @Override
    double evaluate()
    {
        return sign*value;
    }

    @Override
    public String toString()
    {
        String sgn = sign<0 ? "-" : "";
        var format = new DecimalFormat("0.##", new DecimalFormatSymbols(Locale.US));
        return sgn + format.format(value);
    }

    @Override
    public Node diff(Variable v)
    {
        return new Constant(0);
    }
}