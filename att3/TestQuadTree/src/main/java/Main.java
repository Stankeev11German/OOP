
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        DrawPanel dp = new DrawPanel();
        dp.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        dp.setSize(400, 400);
        dp.setVisible(true);
        QuadTree qt = new QuadTree(new Rectangle(0, 0, 300, 300));
        qt.insert(new Point(100, 100));
        qt.insert(new Point(100, 200));
        qt.insert(new Point(100, 50));
        qt.insert(new Point(200, 200));
        qt.insert(new Point(170, 170));
        qt.insert(new Point(350, 350));
        qt.insert(new Point(1, 1));
        ArrayList<Point> pointsInRange = new ArrayList<>();
        qt.query(new Rectangle(0, 0, 300, 300), pointsInRange);
        System.out.println(pointsInRange);
    }
}