package org.cis1200.play2048;

import org.cis1200.play2048.gamefiles.Run2048;

import javax.swing.*;

public class Game {
    public static void main(String[] args) {
        Runnable game = new Run2048();

        SwingUtilities.invokeLater(game);
    }
}