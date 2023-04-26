package org.cis1200.play2048.gamefiles;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Paths;
import java.util.*;
import javax.swing.*;

public class Grid extends JPanel{
    private Tile[][] gameboard;
    private int score;
    private int topscore = 0;
    private boolean changedState = false;
    private LinkedList<Tile[][]> recentmoves = new LinkedList<>();
    private LinkedList<Integer> recentscores = new LinkedList<>();
    public static final int GRID_DIM = 4;
    public static final int GRID_SIZE = 800;
    private final JLabel status;
    private final JLabel scorelabel;
    private final JLabel topscorelabel;
    static final String PATH_TO_SAVEFILE = "savefile/save.txt";

    public Grid(JLabel status, JLabel scorelabel, JLabel topscorelabel) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setFocusable(true);

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                changedState = false;
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    slideLeft();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    slideRight();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    slideDown();
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    slideUp();
                }

                updateStatus();
                repaint();
            }
        });

        this.status = status;
        this.scorelabel = scorelabel;
        this.topscorelabel = topscorelabel;
    }

    public void updateRecentMoves() {
        if (changedState) {
            spawnTile();
            Tile[][] copy = new Tile[GRID_DIM][GRID_DIM];
            for (int i = 0; i < GRID_DIM; i++) {
                for (int j = 0; j < GRID_DIM; j++) {
                    copy[i][j] = gameboard[i][j];
                }
            }
            recentmoves.add(copy);
            recentscores.add(score);
        }
    }

    public void updateStatus() {
        if (WinCheck()) {
            status.setText("You Win!");
            requestFocusInWindow();
        } else if (gameOver()) {
            // could've also checked if changedState is false and nonzero tile in every cell
            status.setText("Game Over!");
        } else if (changedState) {
            scorelabel.setText(" Score: " + score + " ");
            topscore = Math.max(topscore, score);
            topscorelabel.setText(" Top Score: " + topscore + " ");
        }
    }

    public void spawnTile() {
        LinkedList<Integer> xcoord = new LinkedList<>();
        LinkedList<Integer> ycoord = new LinkedList<>();
        for (int i = 0; i < GRID_DIM; i++) {
            for (int j = 0; j < GRID_DIM; j++) {
                if (gameboard[i][j].getValue() == 0) {
                    xcoord.add(i);
                    ycoord.add(j);
                }
            }
        }
        int rand = (int) (Math.random() * xcoord.size());
        int i = xcoord.get(rand);
        int j = ycoord.get(rand);
        int randv = 2 * (1 + (int) (Math.random() * 2));
        gameboard[i][j] = new Tile(200*j, 200*i, randv, 200);
    }

    public void slideLeft() {
        for (int i = 0; i < GRID_DIM; i++) {
            int[] cva = condensedValueArray(gameboard[i], true);
            for (int j = 0; j < GRID_DIM; j++) {
                gameboard[i][j] = new Tile(200*j, 200*i, cva[j], 200);
            }
        }
        updateRecentMoves();
    }

    public void slideRight() {
        for (int i = 0; i < GRID_DIM; i++) {
            int[] cva = condensedValueArray(gameboard[i], false);
            for (int j = 0; j < GRID_DIM; j++) {
                gameboard[i][j] = new Tile(200*j, 200*i, cva[j], 200);
            }
        }
        updateRecentMoves();
    }

    public void slideUp() {
        for (int i = 0; i < GRID_DIM; i++) {
            Tile[] column = new Tile[GRID_DIM];
            for (int j = 0; j < GRID_DIM; j++) {
                column[j] = gameboard[j][i];
            }
            int[] cva = condensedValueArray(column, true);
            for (int j = 0; j < GRID_DIM; j++) {
                gameboard[j][i] = new Tile(200*i, 200*j, cva[j], 200);
            }
        }
        updateRecentMoves();
    }

    public void slideDown() {
        for (int i = 0; i < GRID_DIM; i++) {
            Tile[] column = new Tile[GRID_DIM];
            for (int j = 0; j < GRID_DIM; j++) {
                column[j] = gameboard[j][i];
            }
            int[] cva = condensedValueArray(column, false);
            for (int j = 0; j < GRID_DIM; j++) {
                gameboard[j][i] = new Tile(200*i, 200*j, cva[j], 200);
            }
        }
        updateRecentMoves();
    }

    public int[] condensedValueArray(Tile[] ar, boolean left) {
        int[] vals = new int[GRID_DIM];
        for (int i = 0; i < GRID_DIM; i++) {
            if (left) {
                vals[i] = ar[i].getValue();
            } else { // we want to condense to the right so we flip the array for now
                vals[GRID_DIM - i - 1] = ar[i].getValue();
            }
        }
        int ind = 0;
        // collapse null spaces
        for (int i = 0; i < GRID_DIM; i++) {
            if (vals[i] != 0) {
                vals[ind] = vals[i];
                if (ind != i) {
                    vals[i] = 0;
                    changedState = true;
                }
                ind++;
            }
        }
        // collapse values to the left
        for (int i = 0; i < GRID_DIM-1; i++) {
            if (vals[i] != 0 && vals[i+1] != 0) {
                if (vals[i] == vals[i+1]) {
                    changedState = true;
                    vals[i] *= 2;
                    score += vals[i];
                    vals[i+1] = 0;
                }
            }
        }
        ind = 0;
        // collapse null spaces again
        for (int i = 0; i < GRID_DIM; i++) {
            if (vals[i] != 0) {
                vals[ind] = vals[i];
                if (ind != i) {
                    vals[i] = 0;
                    changedState = true;
                }
                ind++;
            }
        }
        // undo flip if we want to condense right
        if (!left) {
            for(int i = 0; i < GRID_DIM / 2; i++) {
                int temp = vals[i];
                vals[i] = vals[GRID_DIM - i - 1];
                vals[GRID_DIM - i - 1] = temp;
            }
        }
        return vals;
    }

    public boolean gameOver() {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        for (int i = 0; i < GRID_DIM; i++) {
            for (int j = 0; j < GRID_DIM; j++) {
                if (gameboard[i][j].getValue() == 0){
                    return false;
                }
                for (int k = 0; k < 4; k++) {
                     if (i+dx[k] >= 0 && i+dx[k] < GRID_DIM && j+dy[k] >= 0 && j+dy[k] < GRID_DIM
                             && gameboard[i][j].getValue() == gameboard[i+dx[k]][j+dy[k]].getValue()) {
                         return false;
                     }
                }
            }
        }
        return true;
    }

    public boolean WinCheck() {
        for (int i = 0; i < GRID_DIM; i++) {
            for (int j = 0; j < GRID_DIM; j++) {
                if (gameboard[i][j].getValue() == 2048){
                    return true;
                }
            }
        }
        return false;
    }

    public void undo() {
        if (recentmoves.size() > 1 && recentscores.size() > 1) {
            recentmoves.removeLast();
            Tile[][] nb = recentmoves.getLast();
            for (int i = 0; i < GRID_DIM; i++) {
                for (int j = 0; j < GRID_DIM; j++) {
                    gameboard[i][j] = new Tile(200*j, 200*i, nb[i][j].getValue(), 200);
                }
            }
            changedState = false;
            recentscores.removeLast();
            score = recentscores.getLast();
            scorelabel.setText(" Score: " + score + " ");
            status.setText("Running...");
            repaint();
        }
        requestFocusInWindow();
    }

    public void saveGame() {
        File file = Paths.get(PATH_TO_SAVEFILE).toFile();
        try {
            FileWriter fw = new FileWriter(file, false);
            fw.write(score + "," + topscore + "\n");
            for (int i = 0; i < GRID_DIM; i++) {
                String line = "";
                for (int j = 0; j < GRID_DIM; j++) {
                    line += gameboard[i][j].getValue() + ",";
                }
                fw.write(line.substring(0,line.length()-1) + "\n");
            }
            fw.close();

            requestFocusInWindow();
        } catch (IOException e) {
            return;
        }
    }

    public void loadGame() {
        try {
            File save = new File(PATH_TO_SAVEFILE);
            BufferedReader br = new BufferedReader(new FileReader(save));
            String scores = br.readLine();
            String[] sc = scores.split(",");
            score = Integer.parseInt(sc[0]);
            scorelabel.setText(" Score: " + score + " ");
            topscore = Integer.parseInt(sc[1]);
            topscorelabel.setText(" Top Score: " + topscore + " ");
            String line;
            for (int i = 0; i < GRID_DIM; i++) {
                line = br.readLine();
                String[] vals = line.split(",");
                for (int j = 0; j < GRID_DIM; j++) {
                    gameboard[i][j] = new Tile(200*j, 200*i, Integer.parseInt(vals[j]), 200);
                }
            }
            br.close();

            requestFocusInWindow();
            repaint();
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException();
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    public void reset() {
        gameboard = new Tile[GRID_DIM][GRID_DIM];
        for (int i = 0; i < GRID_DIM; i++) {
            for (int j = 0; j < GRID_DIM; j++) {
                gameboard[i][j] = new Tile(200*j, 200*i, 0, 200);
            }
        }
        score = 0;
        recentmoves = new LinkedList<>();
        recentscores = new LinkedList<>();
        spawnTile();
        changedState = true;
        updateRecentMoves();
        changedState = false;
        status.setText("Running...");
        scorelabel.setText(" Score: 0 ");

        requestFocusInWindow();
        repaint();
    }

    public int[][] getGrid() {
        int[][] v = new int[GRID_DIM][GRID_DIM];
        for (int i = 0; i < GRID_DIM; i++) {
            for (int j = 0; j < GRID_DIM; j++) {
                v[i][j] = gameboard[i][j].getValue();
            }
        }
        return v;
    }

    public void setGrid(int[][] values) {
        gameboard = new Tile[GRID_DIM][GRID_DIM];
        for (int i = 0; i < GRID_DIM; i++) {
            for (int j = 0; j < GRID_DIM; j++) {
                gameboard[i][j] = new Tile(200*j, 200*i, values[i][j], 200);
            }
        }
        Tile[][] copy = new Tile[GRID_DIM][GRID_DIM];
        for (int i = 0; i < GRID_DIM; i++) {
            for (int j = 0; j < GRID_DIM; j++) {
                copy[i][j] = gameboard[i][j];
            }
        }
        recentmoves.add(copy);
        recentscores.add(score);
        changedState = false;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < GRID_DIM; i++) {
            for (int j = 0; j < GRID_DIM; j++) {
                gameboard[i][j].draw(g);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(GRID_SIZE, GRID_SIZE);
    }
}
