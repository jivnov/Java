import java.awt.*;
import java.util.Random;

public class Tree implements XmasShape {
    double Sx, Sy, overlap;
    int X, Y, levels;

    public Tree(int x, int y, double scalex, double scaley) {
        this.X = x;
        this.Y = y;
        this.Sx = scalex;
        this.Sy = scaley;
        this.levels = 4;
        this.overlap = 0.6;
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(X, Y);
        g2d.scale(Sx, Sy);
    }

    @Override
    public void render(Graphics2D g2d) {
        Random rd = new Random();
        g2d.setColor(new Color(126, 60, 26));
        g2d.fillRect(-20, getPos(levels + 1, levels + 2), 40, 80);
        for (var i = levels; i >= 0; i--)
        {
            var scale = i + 1;
            var noise = rd.nextDouble() * 0.2 + 0.9;
            Branch b = new Branch(0, getPos(i, scale), noise*scale, scale);
            b.draw(g2d);
        }
    }

    int getPos(int lv, double s) {
        return (int)(10 * lv * s * overlap);
    }
}