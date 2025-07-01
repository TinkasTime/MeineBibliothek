package SimpleCanvas;

import javax.swing.JPanel;

import SimpleCanvas.SimpleCanvas.DrawingMode;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

class DrawingCanvas extends JPanel {

    private List<DrawingStroke> drawingStrokes = new ArrayList<>();
    private DrawingStroke currenStroke = null;
    private Point lastPoint = null;
    private DrawingMode currentCanvasMode = DrawingMode.PEN;

    public DrawingCanvas() {
        setDrawingMode(currentCanvasMode);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastPoint = e.getPoint();
                currenStroke = new DrawingStroke(currentCanvasMode);
                currenStroke.addPoint(lastPoint);
                drawingStrokes.add(currenStroke);
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                lastPoint = null;
                currenStroke = null;
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (lastPoint != null && currenStroke != null) {
                    Point currentPoint = e.getPoint();
                    currenStroke.addPoint(currentPoint);

                    Graphics g = getGraphics();
                    if (g != null) {
                        if (currentCanvasMode == DrawingMode.PEN) {
                            g.setColor(Color.BLACK);
                        } else {
                            g.setColor(Color.WHITE);
                        }
                        g.drawLine(lastPoint.x, lastPoint.y, currentPoint.x, currentPoint.y);
                        g.dispose();
                    }
                    lastPoint = currentPoint;
                }
            }
        });
    }

    public void setDrawingMode(DrawingMode mode) {
        this.currentCanvasMode = mode;
        if (mode == DrawingMode.PEN) {
            setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        } else {
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        for (DrawingStroke stroke : drawingStrokes) {
            if (stroke.getMode() == DrawingMode.PEN) {
                g.setColor(Color.BLACK);
            } else {
                g.setColor(Color.WHITE);
            }

            List<Point> points = stroke.getPoints();
            for (int i = 0; i < points.size()-1; i++) {
                Point p1 = points.get(i);
                Point p2 = points.get(i+1);
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
    }
}
