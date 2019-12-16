public class Variable extends Node {
    String name;
    double value;

    Variable(String name)
    {
        this.name = name;
    }

    Variable(String name, double v)
    {
        this.name = name;
        this.value = v;
    }

    public void setValue(double d)
    {
        value = d;
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
        return sgn + name;
    }

    @Override
    public Node diff(Variable v)
    {
        if (v.name.equals(name))
            return new Constant(sign);
        else
            return new Constant(0);
    }
}