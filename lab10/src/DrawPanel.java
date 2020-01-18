import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DrawPanel extends JPanel {
    public java.util.List<XmasShape> shapes = new ArrayList<>();

    public DrawPanel() {
        setBackground(new Color(92, 124, 255));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        g.setColor(Color.red);
        g.setFont(new Font("Helvetica", Font.ITALIC, 21));
        g.drawString("Wiesołych Świąt", 435, 20);

        for (var s : shapes)
            s.draw(g2d);
    }
}