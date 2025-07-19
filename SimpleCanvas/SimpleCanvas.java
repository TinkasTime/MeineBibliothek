package SimpleCanvas;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class SimpleCanvas {

    private static DrawingMode currentMode = DrawingMode.NONE;

    private static JButton undoButton;
    private static DrawingCanvas drawingCanvas;

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
        undoButton = new JButton("Undo");

        buttonPanel.add(penButton);
        buttonPanel.add(eraserButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(undoButton);

        frame.add(buttonPanel, BorderLayout.NORTH);

        drawingCanvas = new DrawingCanvas();
        drawingCanvas.setUndoButtonCallback(() -> updateUndoButtonState());
        frame.add(drawingCanvas, BorderLayout.CENTER);

        frame.setVisible(true);

        penButton.setEnabled(true);
        eraserButton.setEnabled(true);
        updateUndoButtonState();
        drawingCanvas.setDrawingMode(currentMode);

        penButton.addActionListener(e -> {
            penButton.setEnabled(false);
            eraserButton.setEnabled(true);
            currentMode = DrawingMode.PEN;
            drawingCanvas.setDrawingMode(currentMode);
        });

        eraserButton.addActionListener(e -> {
            penButton.setEnabled(true);
            eraserButton.setEnabled(false);
            currentMode = DrawingMode.ERASER;
            drawingCanvas.setDrawingMode(currentMode);
        });

        clearButton.addActionListener(e -> {
            drawingCanvas.clearDrawing();
            penButton.setEnabled(true);
            eraserButton.setEnabled(true);
            currentMode = DrawingMode.NONE;
            drawingCanvas.setDrawingMode(currentMode);
            updateUndoButtonState();
        });

        undoButton.addActionListener(e -> {
            drawingCanvas.undoLastStroke();
            updateUndoButtonState();
        });
    }

    public static void updateUndoButtonState() {
        if (undoButton != null && drawingCanvas != null) {
            undoButton.setEnabled(drawingCanvas.hasStrokes());
        }
    }
}