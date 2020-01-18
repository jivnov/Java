import java.awt.*;

public class Platki implements XmasShape {
    int x;
    int y;
    double scale;
    Color fillColor;

    public Platki(int x, int y, double scale) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.fillColor = Color.white;
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(fillColor);
        g2d.drawString("*", 110, 110);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x, y);
        g2d.scale(scale, scale);
    }
}