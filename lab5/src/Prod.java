import java.util.*;

public class Prod extends Node {
    List<Node> args = new ArrayList<>();

    Prod()
    {
    }

    Prod(Node n1)
    {
        args.add(n1);
    }

    Prod(double c)
    {
        this(new Constant(c));
    }

    Prod(Node n1, Node n2)
    {
        args.add(n1);
        args.add(n2);
    }

    Prod(double c, Node n)
    {
        this(new Constant(c), n);
    }

    Prod mul(Node n)
    {
        args.add(n);
        return this;
    }

    Prod mul(double c)
    {
        return mul(new Constant(c));
    }

    @Override
    double evaluate()
    {
        double result = 1;
        for (Node n : args) {
            result *= n.evaluate();
        }
        return sign*result;
    }

    @Override
    int getArgumentsCount()
    {
        return args.size();
    }

    @Override
    public String toString()
    {
        StringBuilder b =  new StringBuilder();
        if(sign < 0)
            b.append("-");
        b.append("(");
        for (Node n : args)
        {
            b.append(n.toString());
            b.append("*");
        }
        b.delete(b.length()-1, b.length());
        b.append(")");
        return b.toString();
    }

    @Override
    public Node diff(Variable v)
    {
        Sum s = new Sum();
        for (var i = 0; i < args.size(); i++)
        {
            Prod m = new Prod();
            for (var j = 0; j < args.size(); j++)
            {
                Node a = args.get(j);
                if (j != i)
                    m.mul(a.diff(v));
                else
                    m.mul(a);
            }
            s.add(m);
        }
        return s;
    }
}