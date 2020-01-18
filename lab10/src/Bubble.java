
import java.awt.*;

public class Bubble implements XmasShape {
    int x;
    int y;
    double scale;
    Color lineColor;
    Color fillColor;

    public Bubble(int x, int y, double scale, Color clr) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.lineColor = clr;
        this.fillColor = clr;
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(fillColor);
        g2d.fillOval(0,0,50,50);
        g2d.setColor(lineColor);
        g2d.drawOval(0,0,50,50);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}