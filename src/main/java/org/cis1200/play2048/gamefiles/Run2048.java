package org.cis1200.play2048.gamefiles;

import javax.swing.*;
import java.awt.*;

public class Run2048 implements Runnable{
    public void run() {
        final JFrame frame = new JFrame("2048");
        frame.setLocation(300, 300);

        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Running...");
        status_panel.add(status);
        final JLabel score = new JLabel(" Score: 0 ");
        final JLabel topscore = new JLabel(" Top Score: 0 ");
        score.setBorder(BorderFactory.createLineBorder(Color.black));
        topscore.setBorder(BorderFactory.createLineBorder(Color.black));

        final Grid board = new Grid(status, score, topscore);
        frame.add(board, BorderLayout.CENTER);

        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        final JButton howtoplay = new JButton();
        howtoplay.setText("How To Play");
        howtoplay.addActionListener(e -> JOptionPane.showMessageDialog(frame,
                "2048 is played on a plain 4×4 grid, with numbered tiles that slide " + "\n" +
                        "when a player moves them using the four arrow keys. Every turn, a new tile " + "\n" +
                        "randomly appears in an empty spot on the board with a value of either 2 or 4." + "\n" +
                        "Tiles slide as far as possible in the chosen direction until they are stopped by" + "\n" +
                        " either another tile or the edge of the grid. If two tiles of the same number " + "\n" +
                        "collide while moving, they will merge into a tile with the total value of the two " + "\n" +
                        "tiles that collided. The resulting tile cannot merge with another tile " + "\n" +
                        "again in the same move.\n" +
                        "\n" +
                        "If a move causes three consecutive tiles of the same value to slide together, " + "\n" +
                        "only the two tiles farthest along the direction of motion will combine. " + "\n" +
                        "If all four spaces in a row or column are filled with tiles of the same value, " + "\n" +
                        "a move parallel to that row/column will combine the first two and last two." + "\n" +
                        " A scoreboard on the upper-right keeps track of the user's score. The user's score " + "\n" +
                        "starts at zero, and is increased whenever two tiles combine, " + "\n" +
                        "by the value of the new tile.\n" +
                        "\n" +
                        "The game is won when a tile with a value of 2048 appears on the board. " + "\n" +
                        "When the player has no legal moves (there are no empty spaces and no adjacent " + "\n" +
                        "tiles with the same value), the game ends."));
        howtoplay.addActionListener(e -> board.requestFocusInWindow());
        final JButton undo = new JButton("Undo Move");
        undo.addActionListener(e -> board.undo());
        final JButton reset = new JButton("Reset");
        reset.addActionListener(e -> board.reset());
        final JButton save = new JButton("Save Game");
        save.addActionListener(e -> board.saveGame());
        final JButton load = new JButton("Load Game");
        load.addActionListener(e -> board.loadGame());
        control_panel.add(howtoplay);
        control_panel.add(undo);
        control_panel.add(reset);
        control_panel.add(save);
        control_panel.add(load);
        control_panel.add(score);
        control_panel.add(topscore);


        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        board.reset();
    }
}
