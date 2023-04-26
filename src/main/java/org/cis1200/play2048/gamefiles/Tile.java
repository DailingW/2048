package org.cis1200.play2048.gamefiles;

import java.awt.*;

public class Tile {
    private int px;
    private int py;
    private int value;
    private Color color;
    private int tilesize;


    public Tile(int x, int y, int v, int ts) {
        this.px = x;
        this.py = y;
        this.value = v;
        this.tilesize = ts;
        updateColor();
    }

    public void updateColor() {
        if(value == 2) {
            color = new Color(238, 228, 218);
        } else if(value == 4) {
            color = new Color(237, 224, 200);
        } else if(value == 8) {
            color = new Color(242, 177, 121);
        } else if(value == 16) {
            color = new Color(245, 149, 99);
        } else if(value == 32) {
            color = new Color(246, 124, 95);
        } else if(value == 64) {
            color = new Color(246, 94, 59);
        } else if(value == 128) {
            color = new Color(237, 207, 114);
        } else if(value == 256) {
            color = new Color(237, 204, 97);
        } else if(value == 512) {
            color = new Color(237, 200, 80);
        } else if(value == 1024) {
            color = new Color(237, 197, 63);
        } else if(value == 2048) {
            color = new Color(237, 194, 46);
        } else {
            color = Color.GRAY;
        }
    }

    public int getPx() {
        return this.px;
    }

    public int getPy() {
        return this.py;
    }

    public int getValue() {
        return this.value;
    }

    public int getTilesize() { return this.tilesize; }

    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillRoundRect(this.getPx(), this.getPy(), this.getTilesize(), this.getTilesize(), 25, 25);
        if (value != 0) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Dialog", Font.PLAIN, 80));
            FontMetrics fm = g.getFontMetrics();
            int asc = fm.getAscent();
            int des = fm.getDescent();
            String s = Integer.toString(value);
            g.drawString(s, px + ((tilesize - fm.stringWidth(s)) / 2), py + (asc + (tilesize - (asc + des)) / 2));
        }
    }
}
