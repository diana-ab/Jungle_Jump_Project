package ui;

import engine.GameEngine;
import entities.BasePlatform;
import entities.Player;
import entities.PowerUp;
import input.PlayerKeyListener;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends BasePanel {
    private static final int SCORE_LABEL_X = 20;
    private static final int SCORE_LABEL_y = 20;


    private GameEngine gameEngine;
    private Player player;
    private int playerStartingX;
    private int playerStartingY;
    private int score;
    private ScoreLabel scoreLabel;
    private String text;
    private int x;
    private int y;


    public GamePanel(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.text = "Score: ";
        this.x = SCORE_LABEL_X;
        this.y = SCORE_LABEL_y;
        this.setLayout(null);
        this.scoreLabel = new ScoreLabel(this.text, this.x, this.y);
        this.add(scoreLabel);
        this.score = 0;
        this.setFocusable(true);
        this.requestFocus();
    }

    public void updateScore(int score) {
        scoreLabel.updateScore(score);
    }

    public boolean gameLost() {
        if (this.gameEngine.isPlayerOutOfBounds()) {
            this.gameEngine.stopGame();
            return true;
        }
        return false;
    }

    public int getPlayerStartingX() {
        return playerStartingX;
    }

    public void startGame() {
        this.playerStartingX = (this.getWidth() / 2) - Player.PLAYER_WIDTH / 2;
        this.playerStartingY = (this.getHeight() / 2) + Player.PLAYER_HEIGHT / 2;
        this.player = new Player(this.playerStartingX, this.playerStartingY);
        this.addKeyListener(new PlayerKeyListener(this.player, this));

        this.gameEngine = new GameEngine(this, this.player);
        this.gameEngine.start();
        this.gameEngine.startGame();
    }

    public void setScore(int score) {
        this.score = score;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        List<BasePlatform> platformsCopy = new ArrayList<>(gameEngine.getPlatforms());
        for (BasePlatform platform : platformsCopy) {
            platform.draw(graphics);
        }


        List<PowerUp> powerUps = new ArrayList<>(this.gameEngine.getPowerUps());
        for (PowerUp powerUp : powerUps) {
           powerUp.draw(graphics);
        }

        this.player.draw(graphics);
        if (this.player.getX() > this.getWidth()) {
            this.player.setX(this.getX() - this.player.getWidth());
        } else if (this.player.getX() + this.player.getWidth() < this.getX()) {
            this.player.setX(this.getWidth());
        }
    }
}
