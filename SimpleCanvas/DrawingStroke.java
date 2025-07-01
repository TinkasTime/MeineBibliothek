package SimpleCanvas;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import SimpleCanvas.SimpleCanvas.DrawingMode;

public class DrawingStroke {
    
    private List<Point> points;
    private DrawingMode mode;

    public DrawingStroke(DrawingMode mode) {
        this.mode = mode;
        this.points = new ArrayList<>();
    }

    public List<Point> getPoints() {
        return points;
    }

    public DrawingMode getMode() {
        return mode;
    }

    public void addPoint(Point p) {
        this.points.add(p);
    }
}
