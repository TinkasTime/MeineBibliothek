package Vier_gewinnt;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Board {

    private final int col = 7;
    private final int row = 6;
    private final int squareSize = 100;
    private int[][] discs = new int[row][col];

    JFrame frame;
    JPanel panel;
    GameLogik logik;

    public static int winner = 0;

    public Board(GameLogik logik) {
        initializeDiscs();
        this.logik = logik;
        createGUI();
    }

    private void initializeDiscs() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                discs[i][j] = 0;
                System.out.print(discs[i][j]);
            }
            System.out.println();
        }
    }

    private void createGUI() {
        frame = new JFrame();
        frame.setTitle("4 gewinnt!");

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawBoard(g);
            }
        };
        panel.setPreferredSize(new Dimension(col*squareSize, row*squareSize));
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                logik.move(discs, e.getX());
                panel.repaint();
            }
        });
        frame.add(panel);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void drawBoard(Graphics g) {
        panel.setBackground(Color.BLUE);
        int x = 0, y = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                x = j * squareSize;
                y = i * squareSize;
                
                if (discs[i][j] == 0) {
                    g.setColor(Color.WHITE);
                } else if (discs[i][j] == 1) {
                    g.setColor(Color.RED);
                } else if (discs[i][j] == 2) {
                    g.setColor(Color.YELLOW);
                }

                g.fillOval(x+5, y+5, squareSize-10, squareSize-10);
            }
        }

        if (winner != 0) {
            g.setColor(Color.BLACK);
            g.fillRect(100, 100, 500, 400);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Digital-7", Font.PLAIN, 80));
            if (winner == 2)
                g.drawString("Red won", 180, 310);
            else if (winner == 1)
                g.drawString("Yellow won", 140, 310);
            panel.setEnabled(false);
        }
    }

}