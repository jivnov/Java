abstract public class Node {
    int sign = 1;

    Node minus()
    {
        sign = -1;
        return this;
    }

    Node plus()
    {
        sign = 1;
        return this;
    }

    int getSign()
    {
        return sign;
    }

    int getArgumentsCount()
    {
        return 0;
    }

    abstract double evaluate();

    public String toString()
    {
        return "";
    }

    abstract Node diff(Variable v);
}