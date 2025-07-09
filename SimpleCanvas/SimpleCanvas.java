package SimpleCanvas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SimpleCanvas {

    public enum DrawingMode {
        PEN, ERASER, NONE
    }

    private static DrawingMode currentMode = DrawingMode.NONE;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("SimpleCanvas");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton penButton = new JButton("Pen");
        JButton eraserButton = new JButton("Eraser");
        JButton clearButton = new JButton("Clear");
        buttonPanel.add(penButton);
        buttonPanel.add(eraserButton);
        buttonPanel.add(clearButton);

        frame.add(buttonPanel, BorderLayout.NORTH);

        DrawingCanvas drawingCanvas = new DrawingCanvas();
        frame.add(drawingCanvas, BorderLayout.CENTER);

        frame.setVisible(true);

        penButton.setEnabled(true);
        eraserButton.setEnabled(true);

        penButton.addActionListener(e -> {
            currentMode = DrawingMode.PEN;
            penButton.setEnabled(false);
            eraserButton.setEnabled(true);
            drawingCanvas.setDrawingMode(currentMode);
        });

        eraserButton.addActionListener(e -> {
            currentMode = DrawingMode.ERASER;
            eraserButton.setEnabled(false);
            penButton.setEnabled(true);
            drawingCanvas.setDrawingMode(currentMode);
        });

        clearButton.addActionListener(e -> {
            eraserButton.setEnabled(true);
            penButton.setEnabled(true);
            drawingCanvas.clearDrawing();
        });
    }
}