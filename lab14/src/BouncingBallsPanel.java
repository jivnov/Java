import javax.swing.*;
import java.awt.*;
import java.util.*;

public class BouncingBallsPanel extends JPanel {
    java.util.List<Ball> balls = new ArrayList<>();
    AnimationThread animationEngine = new AnimationThread();
    boolean stopFlag = true;
    int width, height;

    static class Ball {
        int x = 10;
        int y = 10;
        double vx = 10;
        double vy = 10;
        Color color = Color.red;
        int size = 15;

        public void move(long t) {
            double time = t / 100.0;
            x += vx * time;
            y += vy * time;
        }

        public void bounce(int w, int h) {
            int size2 = size / 2;
            if (x < size2) {
                vx = -vx;
                x = size2;
            }
            if (x > w-size2) {
                vx = -vx;
                x = w-size2;
            }
            if (y < size2) {
                vy = -vy;
                y = size2;
            }
            if (y > h-size2) {
                vy = -vy;
                y = h-size2;
            }
        }

        public void impulse(Ball other) {
            if (this == other)
                return;
            double dx = x - other.x, dy = y - other.y;
            double v = Math.pow(dx, 2) + Math.pow(dy, 2);
            if (v > Math.pow(size,2.1))
                return;
            double dst = Math.sqrt(v),
                    nx = dx / dst, ny = dy / dst,
                    dvx = vx - other.vx, dvy = vy - other.vy,
                    strength = dvx * nx + dvy * ny;
            double[] impulse = {nx * strength, ny * strength};
            vx -= impulse[0];
            other.vx += impulse[0];
            vy -= impulse[1];
            other.vy += impulse[1];
        }

        public void paint(Graphics2D g2d) {
            int size2 = size / 2;
            g2d.setColor(color);
            g2d.fillOval(x-size2, y-size2, size, size);
        }
    }

    class AnimationThread extends Thread{
        static final long timeStep = 50;
        @Override
        public void run(){
            while (!stopFlag) {
                try {
                    for (Ball b : balls) {
                        b.move(timeStep);
                        b.bounce(width, height);
                        for (Ball other : balls)
                            b.impulse(other);
                    }
                    repaint();
                    sleep(timeStep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    BouncingBallsPanel() {
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));
        setBackground(new Color(122,255, 22));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (Ball b : balls)
            b.paint(g2d);
    }

    void onStart() {
        width = getWidth();
        height = getHeight();
        stopFlag = false;
        if (!animationEngine.isAlive()) {
            System.out.println("Start or resume animation thread");
            animationEngine = new AnimationThread();
            animationEngine.start();
        }
    }

    void onStop() {
        System.out.println("Suspend animation thread");
        stopFlag = true;
    }

    void onPlus() {
        System.out.println("Add a ball");
        var b = new Ball();
        balls.add(b);
    }

    void onMinus() {
        System.out.println("Remove a ball");
        if (balls.size() > 0)
            balls.remove(0);
    }
}