package SimpleCanvas;

import javax.swing.JPanel;

import SimpleCanvas.SimpleCanvas.DrawingMode;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

class DrawingCanvas extends JPanel {

    private static final Color DRAW_COLOR = Color.BLACK;
    private static final Color ERASE_COLOR = Color.WHITE;
    private static final Color BACKGROUND_COLOR = Color.WHITE;

    private List<DrawingStroke> drawingStrokes = new ArrayList<>();
    private DrawingStroke currenStroke = null;

    private Point lastPoint = null;
    private DrawingMode currentCanvasMode = DrawingMode.NONE;

    public DrawingCanvas() {
        setDrawingMode(currentCanvasMode);

        addMouseListener(new DrawingMouseListener());
        addMouseMotionListener(new DrawingMouseMotionListener());
    }

    public void setDrawingMode(DrawingMode mode) {
        this.currentCanvasMode = mode;
        if (mode == DrawingMode.PEN) {
            setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        } else if (mode == DrawingMode.ERASER) {
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        } else {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

    public void clearDrawing() {
        drawingStrokes.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());

        Graphics2D g2d = (Graphics2D) g;

        for (DrawingStroke stroke : drawingStrokes) {
            if (stroke.getMode() == DrawingMode.PEN) {
                g2d.setColor(DRAW_COLOR);
            } else {
                g2d.setColor(ERASE_COLOR);
            }

            List<Point> points = stroke.getPoints();
            if (points.size() > 1) {
                for (int i = 0; i < points.size()-1; i++) {
                    Point p1 = points.get(i);
                    Point p2 = points.get(i+1);
                    g.drawLine(p1.x, p1.y, p2.x, p2.y);
                }
            } else if (points.size() == 1) {
                Point p = points.get(0);
                g2d.drawLine(p.x, p.y, p.x, p.y);
            }
        }
    }

    private class DrawingMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if (currentCanvasMode == DrawingMode.PEN || currentCanvasMode == DrawingMode.ERASER) {
                lastPoint = e.getPoint();
                currenStroke = new DrawingStroke(currentCanvasMode);
                currenStroke.addPoint(lastPoint);
                drawingStrokes.add(currenStroke);
                repaint();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (currentCanvasMode == DrawingMode.PEN || currentCanvasMode == DrawingMode.ERASER) {
                lastPoint = null;
                currenStroke = null;
            }
        }
    }

    private class DrawingMouseMotionListener extends MouseAdapter {
        @Override
        public void mouseDragged(MouseEvent e) {
            if ((currentCanvasMode == DrawingMode.PEN || currentCanvasMode == DrawingMode.ERASER) && lastPoint != null && currenStroke != null) {
                Point currentPoint = e.getPoint();
                currenStroke.addPoint(currentPoint);

                Graphics g = getGraphics();
                if (g != null) {
                    if (currentCanvasMode == DrawingMode.PEN) {
                        g.setColor(DRAW_COLOR);
                    } else {
                        g.setColor(ERASE_COLOR);
                    }
                    g.drawLine(lastPoint.x, lastPoint.y, currentPoint.x, currentPoint.y);
                    g.dispose();
                }
                lastPoint = currentPoint;
            }
        }
    }

}
