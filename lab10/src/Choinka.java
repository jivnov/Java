import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Choinka {
    public static void main(String[] args) {
        int winSize[] = {1000, 700};
        JFrame frame = new JFrame("Choinka");
        DrawPanel dp = new DrawPanel();
        Random rd = new Random();

        // Snow:
        for (int i = 0; i < 250; i++)
            dp.shapes.add(new Platki((int)(winSize[0]*rd.nextDouble()), (int)(winSize[1]*rd.nextDouble()), rd.nextDouble() + 0.3));
        // Christmas tree:
        dp.shapes.add(new Tree(500, 50, 1.4, 2.2));
        // Tree star:
        dp.shapes.add(new Platki(464, 124, 8));
        dp.shapes.add(new Bubble(475, 25, 1, new Color(255,255,5)));

        frame.setContentPane(dp);
        frame.setSize(winSize[0], winSize[1]);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);
    }
}