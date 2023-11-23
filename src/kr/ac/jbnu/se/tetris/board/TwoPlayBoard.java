package src.kr.ac.jbnu.se.tetris.board;

import java.awt.Color;
import javax.swing.JPanel;
import src.kr.ac.jbnu.se.tetris.Counter;
import src.kr.ac.jbnu.se.tetris.GameController;
import src.kr.ac.jbnu.se.tetris.RandomBlockGenerator;
import src.kr.ac.jbnu.se.tetris.page.TwoPlayPanel;

public class TwoPlayBoard extends Board {

    private int reduceDelayConst = 3;
    private TwoPlayPanel gamepage;
    private transient TwoPlayBoardStatus boardStatus;

    public TwoPlayBoard(TwoPlayPanel parent){
        super();
        setGameTimer(new Counter(this));
        setBlockGenerator(new RandomBlockGenerator());
        boardStatus = new TwoPlayBoardStatus();
        updateSpeedLabel();
        this.gamepage = parent;
    }

    @Override
    public JPanel getComponent() {
        JPanel panel = super.getComponent();
        JPanel status = boardStatus.getStatus();
        panel.setLayout(null);
        panel.setBackground(new Color(0,0,0,0));
        status.setBounds(350,420,100,110);
        panel.add(status);
        return panel;
    }

    @Override
    public void gameOver() {
        super.gameOver();
        gamepage.raiseGameOverFrame();
    }

    @Override
    public void gameClear() {
        super.gameClear();
    }

    public void updateTimerLabel(String time) {
        System.out.println("In a TwoPlayerMode, the updateTimerLabel is not needed");
    }

    @Override
    public int removeFullLines() {
        int ret = super.removeFullLines();
        updateDropSpeed();
        return ret;
    }

    private void updateDropSpeed(){
        gamepage.updateDropSpeed();
    }
    public void setPlayerName(String name){
        boardStatus.updatePlayerLabel(name);
    }

    public void reduceDelay(int num){
        if(num < 0) return;
        GameController controller = getController();
        controller.minusDelay(reduceDelayConst*num);
    }

    public void updateSpeedLabel(){
        GameController controller = getController();
        int delay = controller.getDelay();
        Double dropSpeed = 1000.0 / delay;
        String speed = String.format("%.2f/s", dropSpeed);
        boardStatus.updateDropSpeedLabel(speed);
    }

}
