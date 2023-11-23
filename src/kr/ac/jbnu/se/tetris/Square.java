package src.kr.ac.jbnu.se.tetris;

import java.awt.Graphics;

public interface Square {
    public abstract int squareWidth();
    public abstract int squareHeight();
    public abstract void drawSquare(Graphics g, int x, int y, Tetrominoes shape);
}
