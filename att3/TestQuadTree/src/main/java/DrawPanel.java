import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DrawPanel extends JFrame{
    private Rectangle bord = new Rectangle(0, 0, 400, 400);
    private Rectangle range = new Rectangle(100, 100, 100, 70);
    private ArrayList<Point> pointsInRange = new ArrayList<>();
    QuadTree qt = new QuadTree(bord);
    public DrawPanel() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point p = new Point(e.getX(), e.getY());
                qt.insert(p);
                repaint();
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = new Point(e.getX(), e.getY());
                qt.insert(p);
                repaint();
            }
        });
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                qt.query(range, pointsInRange);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D gr = (Graphics2D) g;
        gr.setColor(Color.black);
        gr.fillRect(0, 0, 400, 400);
        qt.show(gr);
        gr.setColor(Color.green);
        for(Point p: pointsInRange) {
            gr.fillOval(p.x, p.y, 4, 4);
        }
        if(pointsInRange.size() != 0) {
            gr.setColor(Color.yellow);
            gr.draw(range);
        }
    }
}