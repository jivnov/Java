import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.time.LocalTime;

class ClockGui extends JPanel {
    LocalTime time = LocalTime.now();
    private static final int CLOCK_FACE_SIZE = 270;

    public ClockGui() {
        (new ClockThread()).start();
        setOpaque(false);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Clock");
        frame.setContentPane(new ClockGui());
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d=(Graphics2D)g;
        g2d.translate(getWidth()/2,getHeight()/2);
        AffineTransform saveAT = g2d.getTransform();

        for(int i = 1; i <= 12; i++){
            AffineTransform at = new AffineTransform();
            at.rotate(2*Math.PI/12*i);
            Point2D src = new Point2D.Float(0,-120);
            Point2D trg = new Point2D.Float();
            at.transform(src,trg);
            g2d.drawString(Integer.toString(i),(int)trg.getX()-5,(int)trg.getY()+5);

            g2d.rotate(2*Math.PI/12*i);
            g2d.drawLine(0, 100, 0, 110);
            g2d.drawOval(-CLOCK_FACE_SIZE / 2, -CLOCK_FACE_SIZE / 2, CLOCK_FACE_SIZE, CLOCK_FACE_SIZE);
            g2d.setTransform(saveAT);
        }

        var secAngle = time.getSecond() * 2 * Math.PI / 60;
        var minAngle = time.getMinute() * 2 * Math.PI / 60 + secAngle / 60;
        var hourAngle = time.getHour()%12 * 2 * Math.PI / 12 + minAngle / 12;

        g2d.rotate(hourAngle);
        g2d.drawLine(0,0,0,-50);
        g2d.setTransform(saveAT);

        g2d.rotate(minAngle);
        g2d.drawLine(0,0,0,-80);
        g2d.setTransform(saveAT);

        g2d.rotate(secAngle);
        g2d.drawLine(0,0,0,-100);
        g2d.setTransform(saveAT);
    }

    class ClockThread extends Thread {
        @Override
        public void run() {
            for (;;)
            {
                time = LocalTime.now();
                System.out.printf("%02d:%02d:%02d\n", time.getHour(), time.getMinute(), time.getSecond());
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();
            }
        }
    }
}