package ui;

import assets.Sound;
import util.ScreenNames;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public static final int WINDOW_WIDTH = 500;
    public static final int WINDOW_HEIGHT = 800;
    public static final int WINDOW_X = 0;
    public static final int WINDOW_Y = 0;


    private CardLayout cardLayout;
    private Container container;
    private ui.Menu menu;
    private GamePanel gamePanel;
    private GameEndPanel gameEndPanel;
    private Sound sound;
    private InstructionsScreen instructionsScreen;


    public MainFrame() {
        this.setTitle("JUNGLE JUMP");
        this.setResizable(false);
        this.setVisible(true);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        this.container = getContentPane();
        this.container.setLayout(this.cardLayout);
        this.menu = new Menu(WINDOW_X, WINDOW_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.container.add(this.menu, ScreenNames.MENU);
        this.cardLayout.show(this.container, ScreenNames.MENU);
        this.gamePanel = new GamePanel(WINDOW_X, WINDOW_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.container.add(this.gamePanel, ScreenNames.GAME);
        this.gameEndPanel = new GameEndPanel(WINDOW_X, WINDOW_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.container.add(this.gameEndPanel, ScreenNames.END_GAME);
        this.sound = new Sound();
        this.sound.playBackgroundSound();
        this.instructionsScreen = new InstructionsScreen(WINDOW_X, WINDOW_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.container.add(this.instructionsScreen, ScreenNames.INSTRUCTIONS);

        this.menu.setStartButtonAction(e -> {
            this.gamePanel.startGame();
            this.cardLayout.show(this.container, ScreenNames.GAME);
            gamePanel.requestFocusInWindow();
            this.lostGame();

        });

        this.menu.setInstructionsButtonAction(e -> {
            this.cardLayout.show(this.container, ScreenNames.INSTRUCTIONS);
        });

        this.gameEndPanel.setBackToMenuButtonAction(e -> {
            this.sound.stopLoseSound();
            this.sound.playBackgroundSound();
            this.cardLayout.show(this.container, ScreenNames.MENU);
        });

        this.instructionsScreen.setReturnButton(e -> {
            this.cardLayout.show(this.container, ScreenNames.MENU);
        });
    }

    private void lostGame() {
        new Thread(() -> {
            try {
                while (!this.gamePanel.gameLost()) {
                    System.out.print("");
                }
                this.sound.stopBackgroundSound();
                this.sound.playLoseSound();
                System.out.println("endGAME");
                this.cardLayout.show(this.container, ScreenNames.END_GAME);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
