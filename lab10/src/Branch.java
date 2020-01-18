import java.awt.*;
import java.util.Random;

public class Branch implements XmasShape {
    double Sx, Sy;
    int X, Y, decoX[], decoY[], decosCount = 20;

    public Branch(int x, int y, double scalex, double scaley) {
        this.X = x;
        this.Y = y;
        this.Sx = scalex;
        this.Sy = scaley;
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(X, Y);
        g2d.scale(Sx, Sy);
    }

    @Override
    public void render(Graphics2D g2d) {
        GradientPaint grad;
        grad = new GradientPaint(0, 0, new Color(0,90,0), 0, 50, new Color(20,200,20));
        g2d.setPaint(grad);
        int x[] = {0, 50, 0, -50};
        int y[] = {0, 20, 20, 20};
        g2d.fillPolygon(x, y, x.length);

        Random rd = new Random();
        setDecoPoints(rd);
        putBubbles(rd, g2d);
    }

    void putBubbles(Random r, Graphics2D g2d) {
        Color allColors[] = {Color.white, Color.red, Color.blue, Color.yellow, Color.orange, Color.cyan, Color.green, Color.magenta};
        for (int i = 0; i < decosCount; i++)
        {
            Color clr = allColors[(int)(r.nextDouble() * allColors.length)];
            Bubble b = new Bubble(decoX[i], decoY[i], r.nextDouble()*0.02 + 0.04, clr);
            b.draw(g2d);
        }
    }

    void setDecoPoints(Random r) {
        decoX = new int[decosCount];
        decoY = new int[decosCount];
        for (int i = 0; i < decosCount; i++)
        {
            decoX[i] = (int)(i * 90.0 / (decosCount-1)) - 45;
            decoY[i] = (int)(10 + 10 * r.nextDouble());
            // Smoothly limit height variation when branch edge reached
            var lim = Math.abs(decoX[i]) / 50.0;
            decoY[i] = (int)(decoY[i] * (1.0 - lim) + 20 * lim);
        }
    }
}