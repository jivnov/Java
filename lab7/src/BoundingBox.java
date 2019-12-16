public class BoundingBox {
    Double xmin = Double.NaN;
    Double ymin = Double.NaN;
    Double xmax = Double.NaN;
    Double ymax = Double.NaN;

    void addPoint(double x, double y)
    {
        if (contains(x, y))
            return;
        if (isEmpty())
        {
            xmin = x; xmax = x;
            ymin = y; ymax = y;
            return;
        }
        if (x <= xmin)
            xmin = x;
        else
            xmax = x;
        if (y <= ymin)
            ymin = y;
        else
            ymax = y;
    }

    boolean contains(double x, double y)
    {
        if (isEmpty())
            return false;
        return x >= xmin && x <= xmax && y >= ymin && y <= ymax;
    }

    boolean contains(BoundingBox bb)
    {
        return contains(bb.xmin, bb.ymin) && contains(bb.xmax, bb.ymax);
    }

    boolean intersects(BoundingBox bb)
    {
        return xmin < bb.xmax && xmax > bb.xmin && ymax < bb.ymin && ymin > bb.ymin;
    }

    BoundingBox add(BoundingBox bb)
    {
        if (bb.xmin < xmin)
            xmin = bb.xmin;
        if (bb.xmax > xmax)
            xmax = bb.xmax;
        if (bb.ymin < ymin)
            ymin = bb.ymin;
        if (bb.ymax > ymax)
            ymax = bb.ymax;
        return this;
    }

    boolean isEmpty()
    {
        return xmin.isNaN() || xmax.isNaN() || ymin.isNaN() || ymax.isNaN();
    }

    double getCenterX()
    {
        if (isEmpty())
            throw new IndexOutOfBoundsException("Not implemented");
        return (xmax + xmin) / 2.0;
    }

    double getCenterY()
    {
        if (isEmpty())
            throw new IndexOutOfBoundsException("Not implemented");
        return (ymax + ymin) / 2.0;
    }

    double distanceTo(BoundingBox bb)
    {
        double R = 6372.800;
        double dLat = Math.toRadians(Math.abs(bb.getCenterY() - getCenterY()));
        double dLon = Math.toRadians(Math.abs(bb.getCenterX() - getCenterX()));
        double a = Math.pow(Math.sin(dLat / 2), 2);
        double b = Math.pow(Math.sin(dLon / 2), 2) * Math.cos(bb.getCenterY()) * Math.cos(getCenterY());
        return 2 * R * Math.asin(Math.sqrt(a + b));
    }
}