package org.example.page;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;
import javax.swing.*;

import lombok.Getter;
import org.example.service.RankingService;

/**
 * mainPage에서 싱글플레이 버튼을 눌렀을 때 나오는 패널을 관리하는 클래스
 */
public class SinglePlayPage extends JPanel {
    static public final int BUTTON_WIDTH = 245;
    static public final int BUTTON_HEIGHT = 70;
    static public final int BUTTON_X = 518;
    static public final int BUTTON_Y = 290;
    @Getter
    private JPanel buttonPanel;
    private final MainPage mainPage;  //메인페이지(parent)
    @Getter
    ImageIcon undoImg, sprintImg, tutorialImg, timeAttackImg,rankingImg;
    @Getter
    JButton sprintBt, tutorialBt, timeAttackBt, undoBt,rankingBtn;
    private Random rand;

    public SinglePlayPage(MainPage parent){
        this.mainPage = parent;
        setLayout(null);
        setSize(1280, 720);
        double x= Math.random()*10000;
        rand= new Random((long) x);
        rand.nextInt();

        buttonPanel = new JPanel();
        buttonInit();
        buttonPanelInit();
        setBackground(new Color(0,0,0, 153));
        setVisible(false);
    }

    /**
     * 버튼이 들어갈 패널을 초기화하고 패널에 추가한다.
     */
    public void buttonPanelInit(){
        buttonPanel.setLayout(null);
        buttonPanel.setSize(1280, 600);
        buttonPanel.setBounds(0, 120,1280, 600);
        buttonPanel.setBackground(new Color(0,0,0, 168));
        add(buttonPanel);
        buttonPanel.setVisible(true);

    }

    /**
     * 버튼 모양과 위치를 설정하고 buttonPanel에 추가하는 메서드
     */
    public void buttonInit(){

        String filePath = "source/button";

        sprintImg = new ImageIcon(filePath+"/sprint.png");    //이미지 아이콘의 위치
        tutorialImg = new ImageIcon(filePath+"/tutorial.png");
        timeAttackImg = new ImageIcon(filePath+"/timeattack.png");
        undoImg = new ImageIcon(filePath+"/undo.png");
        rankingImg = new ImageIcon(filePath+"/rankingImg.png");

        sprintBt = new JButton(sprintImg);  //스프린트
        tutorialBt = new JButton(tutorialImg);  //튜토리얼
        timeAttackBt = new JButton(timeAttackImg);  //타임어택
        undoBt = new JButton(undoImg);  //뒤로가기
        rankingBtn =new JButton(rankingImg);

        sprintBt.setBounds(BUTTON_X, BUTTON_Y, BUTTON_WIDTH,BUTTON_HEIGHT); //버튼들의 위치를 설정한다
        timeAttackBt.setBounds(BUTTON_X, BUTTON_Y + BUTTON_HEIGHT + 30, BUTTON_WIDTH, BUTTON_HEIGHT);
        tutorialBt.setBounds(BUTTON_X, BUTTON_Y +  BUTTON_HEIGHT*2 + 60, BUTTON_WIDTH, BUTTON_HEIGHT);
        undoBt.setBounds(0, 120, BUTTON_WIDTH,BUTTON_HEIGHT);
        rankingBtn.setBounds(BUTTON_X,BUTTON_Y +  BUTTON_HEIGHT*3+90,BUTTON_WIDTH,BUTTON_HEIGHT);

        add(sprintBt);  //버튼 프레임에 추가
        add(timeAttackBt);
        add(tutorialBt);
        add(undoBt);
        add(rankingBtn);

        buttonAction();
    }

    /**
     * 버튼을 눌렀을 때 실행되는 action을 설정힌 메서드
     */
    public void buttonAction(){
        undoBt.addActionListener(new ActionListener() { //뒤로가기 버튼을 눌렀을 때 발생하는 이벤트
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                mainPage.getMainPagePanel().setVisible(true);
            }
        });
        tutorialBt.addActionListener(new ActionListener() { //튜토리얼 버튼을 누를 때 발생하는 이벤트
            @Override
            public void actionPerformed(ActionEvent e) {
                TutorialPanel tutorialPage = new TutorialPanel();
                tutorialPage.getFrame().setVisible(true);
            }
        });
        sprintBt.addActionListener(new ActionListener() {   //스프린트 버튼을 누를 때 발생하는 이벤트
            @Override
            public void actionPerformed(ActionEvent e) {
                SprintPanel sprintPage = new SprintPanel();
                sprintPage.getFrame().setVisible(true);
            }
        });
        timeAttackBt.addActionListener(new ActionListener() {   //타임어택 버튼을 누를 때 발생하는 이벤트
            @Override
            public void actionPerformed(ActionEvent e) {
                TimeAttackPanel timeAttackPage = new TimeAttackPanel();
                timeAttackPage.getFrame().setVisible(true);
            }
        });
        rankingBtn.addActionListener(new ActionListener() {   //랭킹확인 버튼을 누를 때 발생하는 이벤트
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Integer> timelist = RankingService.getInstance().getHighRank(RankingService.TIME);
                List<Integer> list=RankingService.getInstance().getHighRank(RankingService.SCORE);
                String rank="       <<ranking>>   \n";
                for(int i=0;i<list.size();i++){
                    rank=rank+"   "+i+"등" + "    |    "+list.get(i)+"\n";
                    if(i==9){
                        i=list.size();
                    }
                }
                rank=rank+"     <<time ranking>>   \n";
                for(int i=0;i<timelist.size();i++){
                    rank=rank+"   "+i+"등" + "    |    "+timelist.get(i)+"\n";
                    if(i==9){
                        i=timelist.size();
                    }
                }

                JOptionPane.showMessageDialog
                        (null, rank);
            }
        });


    }
}
