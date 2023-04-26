package org.cis1200.play2048;

import org.cis1200.play2048.gamefiles.Grid;
import org.cis1200.play2048.gamefiles.Tile;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;


public class GameTest {

    @Test
    public void CondenseOneTileLeftTest() {
        JLabel jl1 = new JLabel();
        JLabel jl2 = new JLabel();
        JLabel jl3 = new JLabel();
        Grid g = new Grid(jl1, jl2, jl3);
        Tile[] t = new Tile[4];
        for(int i = 0; i < 4; i++) {
            t[i] = new Tile(0, 0, 0, 200);
        }
        t[2] = new Tile(0,0,2,200);
        int[] expected = new int[4];
        expected[0] = 2;
        assertArrayEquals(expected, g.condensedValueArray(t, true));
    }

    @Test
    public void CondenseOneTileRightTest() {
        JLabel jl1 = new JLabel();
        JLabel jl2 = new JLabel();
        JLabel jl3 = new JLabel();
        Grid g = new Grid(jl1, jl2, jl3);
        Tile[] t = new Tile[4];
        for(int i = 0; i < 4; i++) {
            t[i] = new Tile(0, 0, 0, 200);
        }
        t[2] = new Tile(0,0,2,200);
        int[] expected = new int[4];
        expected[3] = 2;
        assertArrayEquals(expected, g.condensedValueArray(t, false));
    }

    @Test
    public void CondenseTwoDifferentTileLeftTest() {
        JLabel jl1 = new JLabel();
        JLabel jl2 = new JLabel();
        JLabel jl3 = new JLabel();
        Grid g = new Grid(jl1, jl2, jl3);
        Tile[] t = new Tile[4];
        for(int i = 0; i < 4; i++) {
            t[i] = new Tile(0, 0, 0, 200);
        }
        t[2] = new Tile(0,0,2,200);
        t[1] = new Tile(0,0,4,200);
        int[] expected = new int[4];
        expected[0] = 4;
        expected[1] = 2;
        assertArrayEquals(expected, g.condensedValueArray(t, true));
    }

    @Test
    public void CondenseTwoDifferentTileRightTest() {
        JLabel jl1 = new JLabel();
        JLabel jl2 = new JLabel();
        JLabel jl3 = new JLabel();
        Grid g = new Grid(jl1, jl2, jl3);
        Tile[] t = new Tile[4];
        for(int i = 0; i < 4; i++) {
            t[i] = new Tile(0, 0, 0, 200);
        }
        t[2] = new Tile(0,0,2,200);
        t[1] = new Tile(0,0,4,200);
        int[] expected = new int[4];
        expected[3] = 2;
        expected[2] = 4;
        assertArrayEquals(expected, g.condensedValueArray(t, false));
    }

    @Test
    public void CondenseTwoIdenticalTileLeftTest() {
        JLabel jl1 = new JLabel();
        JLabel jl2 = new JLabel();
        JLabel jl3 = new JLabel();
        Grid g = new Grid(jl1, jl2, jl3);
        Tile[] t = new Tile[4];
        for(int i = 0; i < 4; i++) {
            t[i] = new Tile(0, 0, 0, 200);
        }
        t[2] = new Tile(0,0,8,200);
        t[1] = new Tile(0,0,8,200);
        int[] expected = new int[4];
        expected[0] = 16;
        assertArrayEquals(expected, g.condensedValueArray(t, true));
    }

    @Test
    public void CondenseTwoIdenticalTileRightTest() {
        JLabel jl1 = new JLabel();
        JLabel jl2 = new JLabel();
        JLabel jl3 = new JLabel();
        Grid g = new Grid(jl1, jl2, jl3);
        Tile[] t = new Tile[4];
        for(int i = 0; i < 4; i++) {
            t[i] = new Tile(0, 0, 0, 200);
        }
        t[2] = new Tile(0,0,8,200);
        t[1] = new Tile(0,0,8,200);
        int[] expected = new int[4];
        expected[3] = 16;
        assertArrayEquals(expected, g.condensedValueArray(t, false));
    }

    @Test
    public void CondenseFourIdenticalTilesLeftTest() {
        JLabel jl1 = new JLabel();
        JLabel jl2 = new JLabel();
        JLabel jl3 = new JLabel();
        Grid g = new Grid(jl1, jl2, jl3);
        Tile[] t = new Tile[4];
        for(int i = 0; i < 4; i++) {
            t[i] = new Tile(0, 0, 2, 200);
        }
        int[] expected = new int[4];
        expected[0] = 4;
        expected[1] = 4;
        assertArrayEquals(expected, g.condensedValueArray(t, true));
    }

    @Test
    public void CondenseFourIdenticalTilesRightTest() {
        JLabel jl1 = new JLabel();
        JLabel jl2 = new JLabel();
        JLabel jl3 = new JLabel();
        Grid g = new Grid(jl1, jl2, jl3);
        Tile[] t = new Tile[4];
        for(int i = 0; i < 4; i++) {
            t[i] = new Tile(0, 0, 2, 200);
        }
        int[] expected = new int[4];
        expected[2] = 4;
        expected[3] = 4;
        assertArrayEquals(expected, g.condensedValueArray(t, false));
    }

    @Test
    public void CondenseTwoIdenticalOneDifferentTilesLeftTest() {
        JLabel jl1 = new JLabel();
        JLabel jl2 = new JLabel();
        JLabel jl3 = new JLabel();
        Grid g = new Grid(jl1, jl2, jl3);
        Tile[] t = new Tile[4];
        for(int i = 0; i < 4; i++) {
            t[i] = new Tile(0, 0, 0, 200);
        }
        t[2] = new Tile(0,0,2,200);
        t[1] = new Tile(0,0,2,200);
        t[0] = new Tile(0,0,8,200);
        int[] expected = new int[4];
        expected[0] = 8;
        expected[1] = 4;
        assertArrayEquals(expected, g.condensedValueArray(t, true));
    }

    @Test
    public void CondenseTwoIdenticalOneDifferentTilesRightTest() {
        JLabel jl1 = new JLabel();
        JLabel jl2 = new JLabel();
        JLabel jl3 = new JLabel();
        Grid g = new Grid(jl1, jl2, jl3);
        Tile[] t = new Tile[4];
        for(int i = 0; i < 4; i++) {
            t[i] = new Tile(0, 0, 0, 200);
        }
        t[2] = new Tile(0,0,2,200);
        t[1] = new Tile(0,0,2,200);
        t[0] = new Tile(0,0,8,200);
        int[] expected = new int[4];
        expected[2] = 8;
        expected[3] = 4;
        assertArrayEquals(expected, g.condensedValueArray(t, false));
    }

    @Test
    public void CondenseThreeIdenticalTilesLeftTest() {
        JLabel jl1 = new JLabel();
        JLabel jl2 = new JLabel();
        JLabel jl3 = new JLabel();
        Grid g = new Grid(jl1, jl2, jl3);
        Tile[] t = new Tile[4];
        for(int i = 0; i < 4; i++) {
            t[i] = new Tile(0, 0, 0, 200);
        }
        t[2] = new Tile(0,0,2,200);
        t[1] = new Tile(0,0,2,200);
        t[0] = new Tile(0,0,2,200);
        int[] expected = new int[4];
        expected[0] = 4;
        expected[1] = 2;
        assertArrayEquals(expected, g.condensedValueArray(t, true));
    }

    @Test
    public void CondenseThreeIdenticalTilesRightTest() {
        JLabel jl1 = new JLabel();
        JLabel jl2 = new JLabel();
        JLabel jl3 = new JLabel();
        Grid g = new Grid(jl1, jl2, jl3);
        Tile[] t = new Tile[4];
        for(int i = 0; i < 4; i++) {
            t[i] = new Tile(0, 0, 0, 200);
        }
        t[2] = new Tile(0,0,2,200);
        t[1] = new Tile(0,0,2,200);
        t[0] = new Tile(0,0,2,200);
        int[] expected = new int[4];
        expected[3] = 4;
        expected[2] = 2;
        assertArrayEquals(expected, g.condensedValueArray(t, false));
    }

    @Test
    public void NoNewTilesIfNothingMovesTest() {
        JLabel jl1 = new JLabel();
        JLabel jl2 = new JLabel();
        JLabel jl3 = new JLabel();
        int[][] v = {{0,0,0,0},
                     {0,0,0,0},
                     {0,0,0,0},
                     {2,0,0,0}};
        Grid g = new Grid(jl1, jl2, jl3);
        g.setGrid(v);
        g.slideLeft();
        g.slideDown();
        assertArrayEquals(v, g.getGrid());
    }

    @Test
    public void SpawnTileTest() {
        JLabel jl1 = new JLabel();
        JLabel jl2 = new JLabel();
        JLabel jl3 = new JLabel();
        int[][] v = {{0,0,0,0},
                     {0,0,0,0},
                     {0,0,0,0},
                     {2,0,0,0}};
        Grid g = new Grid(jl1, jl2, jl3);
        g.setGrid(v);
        g.spawnTile();
        int[][] gb = g.getGrid();
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (gb[i][j] == 2 || gb[i][j] == 4) {
                    count++;
                }
            }
        }
        assertEquals(2, count);
    }

    @Test
    public void UndoMoveTest() {
        JLabel jl1 = new JLabel();
        JLabel jl2 = new JLabel();
        JLabel jl3 = new JLabel();
        int[][] v = {{0,8,0,2},
                     {32,32,0,16},
                     {0,4,4,2},
                     {2,0,0,2}};
        Grid g = new Grid(jl1, jl2, jl3);
        g.setGrid(v);
        g.slideRight();
        g.undo();
        assertArrayEquals(v, g.getGrid());
    }

    @Test
    public void ResetAndUndoMoveTest() {
        JLabel jl1 = new JLabel();
        JLabel jl2 = new JLabel();
        JLabel jl3 = new JLabel();
        Grid g = new Grid(jl1, jl2, jl3);
        g.reset();
        int[][] expected = g.getGrid();
        g.undo();
        assertArrayEquals(expected, g.getGrid());
    }

    @Test
    public void ResetAndUndoMoveTest2() {
        JLabel jl1 = new JLabel();
        JLabel jl2 = new JLabel();
        JLabel jl3 = new JLabel();
        int[][] v = {{0,8,0,2},
                     {32,32,0,16},
                     {0,4,4,2},
                     {2,0,0,2}};
        Grid g = new Grid(jl1, jl2, jl3);
        g.setGrid(v);
        g.reset();
        int[][] expected = g.getGrid();
        g.undo();
        assertArrayEquals(expected, g.getGrid());
    }

    @Test
    public void SaveAndLoadTest() {
        JLabel jl1 = new JLabel();
        JLabel jl2 = new JLabel();
        JLabel jl3 = new JLabel();
        int[][] v = {{0,8,0,2},
                     {32,32,0,16},
                     {0,4,4,2},
                     {2,0,0,2}};
        Grid g = new Grid(jl1, jl2, jl3);
        g.setGrid(v);
        g.saveGame();
        g.slideLeft();
        g.slideDown();
        g.loadGame();
        assertArrayEquals(v, g.getGrid());
    }

    @Test
    public void GameOverTest() {
        JLabel jl1 = new JLabel("Running...");
        JLabel jl2 = new JLabel();
        JLabel jl3 = new JLabel();
        int[][] v = {{8,4,2,4},
                     {16,8,4,2},
                     {32,16,8,4},
                     {64,32,16,8}};
        Grid g = new Grid(jl1, jl2, jl3);
        g.setGrid(v);
        g.updateStatus();
        assertTrue(g.gameOver());
        g.slideUp();
        g.slideDown();
        g.slideRight();
        g.slideLeft();
        int[][] vv = g.getGrid();
        assertArrayEquals(v, vv);
        assertEquals("Game Over!", jl1.getText());
    }

    @Test
    public void WinTest() {
        JLabel jl1 = new JLabel("Running...");
        JLabel jl2 = new JLabel();
        JLabel jl3 = new JLabel();
        int[][] v = {{0,8,0,2},
                {32,32,0,16},
                {0,4,4,2},
                {1024,1024,0,2}};
        Grid g = new Grid(jl1, jl2, jl3);
        g.setGrid(v);
        g.slideLeft();
        g.updateStatus();
        assertEquals("You Win!", jl1.getText());
    }
}
