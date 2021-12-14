import java.awt.*;
import java.util.ArrayList;

public class QuadTree {
    private final int capacity = 5;
    private boolean divide = false;
    private ArrayList<Point> nodes;
    private QuadTree northWest = null;
    private QuadTree northEast = null;
    private QuadTree southWest = null;
    private QuadTree southEast = null;
    private Rectangle boundary;

    public QuadTree(Rectangle boundary) {
        this.boundary = boundary;
        nodes = new ArrayList<Point>();
    }

    public boolean contains(Point p) {
        return p.getX() >= this.boundary.getX() &&
                p.getX() <= this.boundary.getX() + this.boundary.getWidth() &&
                p.getY() >= this.boundary.getY() &&
                p.getY() <= this.boundary.getY() + this.boundary.getHeight();

    }

    public boolean insert(Point p) {
        if (!this.contains(p)) {
            return false;
        }
        if (this.nodes.size() < capacity) {
            this.nodes.add(p);
            return true;
        } else {
            if (!this.divide) {
                subDivide();
            }
            if (this.northWest.insert(p)) {
                return true;
            } else if (this.northEast.insert(p)) {
                return true;
            } else if (this.southEast.insert(p)) {
                return true;
            } else if (this.southWest.insert(p)) {
                return true;
            } else return false;
        }
    }

    private void subDivide() {
        int x = (int) this.boundary.getX();
        int y = (int) this.boundary.getY();
        int w = (int) this.boundary.getWidth();
        int h = (int) this.boundary.getHeight();
        this.northWest = new QuadTree(new Rectangle(x, y, w / 2, h / 2));
        this.northEast = new QuadTree(new Rectangle(x + w / 2, y, w / 2, h / 2));
        this.southEast = new QuadTree(new Rectangle(x + w / 2, y + h / 2, w / 2, h / 2));
        this.southWest = new QuadTree(new Rectangle(x, y + h / 2, w / 2, h / 2));
        this.divide = true;
    }

    public void query(Rectangle range, ArrayList<Point> pointsInRange) {
        if(!this.boundary.intersects(range)) {
            return;
        }else {
            for(Point p:this.nodes) {
                if(range.contains(p)) {
                    pointsInRange.add(p);
                }
                if(this.divide) {
                    this.northEast.query(range, pointsInRange);
                    this.northWest.query(range, pointsInRange);
                    this.southEast.query(range, pointsInRange);
                    this.southWest.query(range, pointsInRange);
                }
            }
        }
    }

    private boolean intersects(Rectangle range) {
        return !(range.x - range.width > this.boundary.x + this.boundary.width ||
                range.x + range.width > this.boundary.x - this.boundary.width ||
                range.y - range.height > this.boundary.y + this.boundary.height ||
                range.y + range.height > this.boundary.y - this.boundary.height);
    }

    public void show(Graphics2D g) {
        g.setColor(Color.white);
        g.drawRect(this.boundary.x, this.boundary.y, this.boundary.width, this.boundary.height);
        if(this.divide) {
            this.northWest.show(g);
            this.northEast.show(g);
            this.southEast.show(g);
            this.southWest.show(g);
        }
        g.setColor(Color.red);
        for(Point p: nodes) {
            g.fillOval(p.x, p.y, 3, 3);
        }
    }
}
