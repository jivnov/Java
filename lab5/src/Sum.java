import java.util.*;

public class Sum extends Node {
    List<Node> args = new ArrayList<>();

    Sum()
    {
    }

    Sum(Node n1, Node n2)
    {
        args.add(n1);
        args.add(n2);
    }

    Sum add(Node n)
    {
        args.add(n);
        return this;
    }

    Sum add(double c)
    {
        args.add(new Constant(c));
        return this;
    }

    Sum add(double c, Node n)
    {
        Node mul = new Prod(c, n);
        args.add(mul);
        return this;
    }

    @Override
    double evaluate()
    {
        double result =0;
        for (Node n : args)
            result += n.evaluate();
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
            b.append("-(");
        for (Node n : args)
        {
            b.append(n.toString());
            b.append("+");
        }
        b.delete(b.length()-1, b.length());
        if(sign < 0)
            b.append(")");
        return b.toString();
    }

    @Override
    public Node diff(Variable v)
    {
        Node zero = new Constant(0);
        Sum cpy = new Sum();
        for (var a : args)
        {
            Node d = a.diff(v);
            if (d != zero)
                cpy.add(d);
        }
        if (cpy.args.size() < 1)
            cpy.add(zero);
        return cpy;
    }
}