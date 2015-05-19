package ru.ifmo.kravchenko;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameView extends JFrame {
    public PanelFieldPlayer panelPlayerPlayer;
    public PanelFieldOpponent panelPlayerOpponent;
    private GameModel model;
    private GameController controller;
    private JMenuItem mntmNewGame;
    private JMenuItem mntmExit;
    private JMenuItem mntmAbout;
    private JMenuItem mntm5;
    private JMenuItem mntm10;
    private JMenuItem mntm15;
    private JMenuItem mntm20;
    private ScoreField panelScore;

    public GameView(GameModel model) {
        this.model = model;
        buildUI();
        this.model.register(panelPlayerPlayer);
        this.model.register(panelPlayerOpponent);
        this.model.register(panelScore);
        this.controller = new GameController(this, model);
        attachController();
    }

    public void update() {
        panelPlayerPlayer.repaint();
        panelPlayerOpponent.repaint();
        panelScore.repaint();
        System.out.println("view update");
    }

    public void attachController() {
        mntmNewGame.addActionListener(controller);
        mntmExit.addActionListener(controller);
        mntm5.addActionListener(controller);
        mntm10.addActionListener(controller);
        mntm15.addActionListener(controller);
        mntm20.addActionListener(controller);
        panelPlayerOpponent.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent arg0) {
                controller.mousePressed(arg0);
            }
        });
    }

    private void buildUI() {
        this.setTitle("SeaBattle");
        this.setResizable(false);
        this.setBounds(400, 300, 483, 228);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width - this.getWidth()) / 2, (screenSize.height - this.getHeight()) / 2);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        panelPlayerPlayer = new PanelFieldPlayer(model.playerFieldPlayer);
        panelPlayerPlayer.setBounds(20, 31, 151, 151);
        this.getContentPane().add(panelPlayerPlayer);

        panelPlayerOpponent = new PanelFieldOpponent(model.playerFieldOpponent);
        panelPlayerOpponent.setBounds(190, 31, 151, 151);
        this.getContentPane().add(panelPlayerOpponent);

        panelScore = new ScoreField(model);

        panelScore.setBounds(370, 31, 90, 151);
        panelScore.setBackground(new Color(225, 225, 255));
        this.getContentPane().add(panelScore);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 477, 21);
        this.getContentPane().add(menuBar);

        JMenu mnGame = new JMenu("Файл");
        menuBar.add(mnGame);

        mntmNewGame = new JMenuItem("Новая игра");
        mnGame.add(mntmNewGame);

        mntmExit = new JMenuItem("Выход");
        mnGame.add(mntmExit);

        JMenu mnProperties = new JMenu("Размер поля");
        menuBar.add(mnProperties);

        mntm5 = new JMenuItem("5 x 5");
        mnProperties.add(mntm5);

        mntm10 = new JMenuItem("10 x 10");
        mnProperties.add(mntm10);

        mntm15 = new JMenuItem("15 x 15");
        mnProperties.add(mntm15);

        mntm20 = new JMenuItem("20 x 20");
        mnProperties.add(mntm20);

        JMenu mnHelp = new JMenu("Помощь");
        menuBar.add(mnHelp);
    }


}