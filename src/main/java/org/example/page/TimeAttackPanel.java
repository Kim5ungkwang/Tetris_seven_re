package org.example.page;

import javax.swing.KeyStroke;
import org.example.board.TimeAttackBoard;

import javax.swing.JPanel;
import org.example.model.KeyInput;
import org.example.score.TimeAttackScore;


public class TimeAttackPanel extends GamePanel {
    private TimeAttackBoard board;



    public TimeAttackPanel(){
        super();
        addBoard(415, 10);
        drawHighScore(new TimeAttackScore().getHighScore());
        addBackground();
    }

    @Override
    public void addBoard(int posX, int posY) {

        board = new TimeAttackBoard(this);
        JPanel boardView = board.getComponent();
        frame.add(boardView);
        boardView.setBounds(posX, posY, 550, 600);
        setAdapter(board);
        board.start();
    }

    @Override
    public void raiseGameClearFrame() {
        String score = Integer.toString(board.getNumLinesRemoved());
        new TimeAttackScore().saveScore(score);
        setGameClearFrame("Score : " + score);
    }
}
