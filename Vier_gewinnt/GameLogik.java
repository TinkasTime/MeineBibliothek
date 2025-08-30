package Vier_gewinnt;

import java.awt.Point;

public class GameLogik {

    private int[][] discs;
    private int player;

    public GameLogik() {
        player = 1;
    }

    public void move(int[][] discs, double x) {
        this.discs = discs;
        pos(x);
        if (win()) {
            Board.winner = player;
        }
    }
    
    private void pos(double i) {
        int x = 0;
        if (i > 100 && i < 200) {
            x = 1;
        } else if (i > 200 && i < 300) {
            x = 2;
        } else if (i > 300 && i < 400) {
            x = 3;
        } else if (i > 400 && i < 500) {
            x = 4;
        } else if (i > 500 && i < 600) {
            x = 5;
        } else if (i > 600 && i < 700) {
            x = 6;
        }
        
        if (legalMove(x, player)) {
            if (player == 1) player = 2;
            else if (player == 2) player = 1;
        }
    }

    public boolean legalMove(int x, int player) {
        int row = discs.length;
        int y = discs.length;
        for (int r = row-1; r >= 0; r-- ) {
            if (discs[0][x] != 0) return false;
            if (discs[r][x] == 0) {
                y = r;
                break;
            }
        }
        discs[y][x] = player;
        return true;
    }

    public boolean win() {
        if (waagerecht()) return true;
        if (senkrecht()) return true;
        if (diagonal()) return true;
        return false;
    }

    private boolean waagerecht() {
        int row = discs.length;
        int col = discs[0].length;
        int w1 = 0, w2 = 0;
        for (int r = 0; r < row; r++) {
            for (int s = 0; s < col; s++) {
                if (discs[r][s] == 1) w1++;
                else w1 = 0;
                if (discs[r][s] == 2) w2++;
                else w2 = 0;
                if (w1 >= 4) return true;
                if (w2 >= 4) return true;
            }
        }
        return false;
    }

    private boolean senkrecht() {
        int row = discs.length;
        int col = discs[0].length;
        int s1 = 0, s2 = 0;
        for (int s = 0; s < col; s++) {
            for (int r = 0; r < row; r++) {
                if (discs[r][s] == 1) s1++;
                else s1 = 0;
                if (discs[r][s] == 2) s2++;
                else s2 = 0;
                if (s1 >= 4) return true;
                if (s2 >= 4) return true;
            }
        }
        return false;
    }

    private boolean diagonal() {
        // 1 O O O O O 0
        // O 1 O O O 0 O
        // O O 1 O 0 O O
        // O O O 1 O O O
        // O O O O O O O
        // O O O O O O O

        if (discs[0][0] == 1 && discs[1][1] == 1 && discs[2][2] == 1 && discs[3][3] == 1)
            return true;
        else if (discs[0][0] == 2 && discs[1][1] == 2 && discs[2][2] == 2 && discs[3][3] == 2)
            return true;
        if (discs[1][0] == 1 && discs[2][1] == 1 && discs[3][2] == 1 && discs[4][3] == 1)
            return true;   
        else if (discs[1][0] == 2 && discs[2][1] == 2 && discs[3][2] == 2 && discs[4][3] == 2)
            return true;     
        if (discs[2][0] == 1 && discs[3][1] == 1 && discs[4][2] == 1 && discs[5][3] == 1)
            return true;
        else if (discs[2][0] == 2 && discs[3][1] == 2 && discs[4][2] == 2 && discs[5][3] == 2)
            return true;

        if (discs[0][1] == 1 && discs[1][2] == 1 && discs[2][3] == 1 && discs[3][4] == 1)
            return true;
        else if (discs[0][1] == 2 && discs[1][2] == 2 && discs[2][3] == 2 && discs[3][4] == 2)
            return true;
        if (discs[1][1] == 1 && discs[2][2] == 1 && discs[3][3] == 1 && discs[4][4] == 1)
            return true;
        else if (discs[1][1] == 2 && discs[2][2] == 2 && discs[3][3] == 2 && discs[4][4] == 2)
            return true;
        if (discs[2][1] == 1 && discs[3][2] == 1 && discs[4][3] == 1 && discs[5][4] == 1)
            return true;
        else if (discs[1][1] == 2 && discs[2][2] == 2 && discs[3][3] == 2 && discs[4][4] == 2)
            return true;
        
        if (discs[0][2] == 1 && discs[1][3] == 1 && discs[2][4] == 1 && discs[3][5] == 1)
            return true;
        else if (discs[0][2] == 2 && discs[1][3] == 2 && discs[2][4] == 2 && discs[3][5] == 2)
            return true;
        if (discs[1][2] == 1 && discs[2][3] == 1 && discs[3][4] == 1 && discs[4][5] == 1)
            return true;
        else if (discs[1][2] == 2 && discs[2][3] == 2 && discs[3][4] == 2 && discs[4][5] == 2)
            return true;
        if (discs[2][2] == 1 && discs[3][3] == 1 && discs[4][4] == 1 && discs[5][5] == 1)
            return true;
        else if (discs[2][2] == 2 && discs[3][3] == 2 && discs[4][4] == 2 && discs[5][5] == 2)
            return true;
        
        if (discs[0][3] == 1 && discs[1][4] == 1 && discs[2][5] == 1 && discs[3][6] == 1)
            return true;
        else if (discs[0][3] == 2 && discs[1][4] == 2 && discs[2][5] == 2 && discs[3][6] == 2)
            return true;
        if (discs[1][3] == 1 && discs[2][4] == 1 && discs[3][5] == 1 && discs[4][6] == 1)
            return true;
        else if (discs[1][3] == 2 && discs[2][4] == 2 && discs[3][5] == 2 && discs[4][6] == 2)
            return true;
        if (discs[2][3] == 1 && discs[3][4] == 1 && discs[4][5] == 1 && discs[5][6] == 1)
            return true;
        else if (discs[2][3] == 2 && discs[3][4] == 2 && discs[4][5] == 2 && discs[5][6] == 2)
            return true;


        
        // 0 O O O O O 1
        // O 0 O O O 1 O
        // O O 0 O 1 O O
        // O O O 1 O O O
        // O O O O O O O
        // O O O O O O O

        if (discs[0][6] == 1 && discs[1][5] == 1 && discs[2][4] == 1 && discs[3][3] == 1)
            return true;
        else if (discs[0][6] == 2 && discs[1][5] == 2 && discs[2][4] == 2 && discs[3][3] == 2)
            return true;
        if (discs[1][6] == 1 && discs[2][5] == 1 && discs[3][4] == 1 && discs[4][3] == 1)
            return true; 
        else if (discs[1][6] == 2 && discs[2][5] == 2 && discs[3][4] == 2 && discs[4][3] == 2)
            return true;       
        if (discs[2][6] == 1 && discs[3][5] == 1 && discs[4][4] == 1 && discs[5][3] == 1)
            return true;
        else if (discs[2][6] == 2 && discs[3][5] == 2 && discs[4][4] == 2 && discs[5][3] == 2)
            return true;

        if (discs[0][5] == 1 && discs[1][4] == 1 && discs[2][3] == 1 && discs[3][2] == 1)
            return true;
        else if (discs[0][5] == 2 && discs[1][4] == 2 && discs[2][3] == 2 && discs[3][2] == 2)
            return true;
        if (discs[1][5] == 1 && discs[2][4] == 1 && discs[3][3] == 1 && discs[4][2] == 1)
            return true;
        else if (discs[1][5] == 2 && discs[2][4] == 2 && discs[3][3] == 2 && discs[4][2] == 2)
            return true;
        if (discs[2][5] == 1 && discs[3][4] == 1 && discs[4][3] == 1 && discs[5][2] == 1)
            return true;
        else if (discs[2][5] == 2 && discs[3][4] == 2 && discs[4][3] == 2 && discs[5][2] == 2)
            return true;
        
        if (discs[0][4] == 1 && discs[1][3] == 1 && discs[2][2] == 1 && discs[3][1] == 1)
            return true;
        else if (discs[0][4] == 2 && discs[1][3] == 2 && discs[2][2] == 2 && discs[3][1] == 2)
            return true;
        if (discs[1][4] == 1 && discs[2][3] == 1 && discs[3][2] == 1 && discs[4][1] == 1)
            return true;
        else if (discs[1][4] == 2 && discs[2][3] == 2 && discs[3][2] == 2 && discs[4][1] == 2)
            return true;
        if (discs[2][4] == 1 && discs[3][3] == 1 && discs[4][2] == 1 && discs[5][1] == 1)
            return true;
        else if (discs[2][4] == 2 && discs[3][3] == 2 && discs[4][2] == 2 && discs[5][1] == 2)
            return true;
        
        if (discs[0][3] == 1 && discs[1][2] == 1 && discs[2][1] == 1 && discs[3][0] == 1)
            return true;
        else if (discs[0][3] == 2 && discs[1][2] == 2 && discs[2][1] == 2 && discs[3][0] == 2)
            return true;
        if (discs[1][3] == 1 && discs[2][2] == 1 && discs[3][1] == 1 && discs[4][0] == 1)
            return true;
        else if (discs[1][3] == 2 && discs[2][2] == 2 && discs[3][1] == 2 && discs[4][0] == 2)
            return true;
        if (discs[2][3] == 1 && discs[3][2] == 1 && discs[4][1] == 1 && discs[5][0] == 1)
            return true;
        else if (discs[2][3] == 2 && discs[3][2] == 2 && discs[4][1] == 2 && discs[5][0] == 2)
            return true;
            
        return false;
    }

}
