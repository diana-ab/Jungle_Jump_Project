package engine;

import entities.BasePlatform;
import entities.Player;
import entities.PowerUp;
import ui.GamePanel;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameEngine extends Thread {
    public static final int SLEEP = 80;
    public static final int COLLISION_THRESHOLD = 20;
    public static final int LEVEL_THRESHOLD = 100;

    private Player player;
    private List<BasePlatform> platforms;
    private GamePanel gamePanel;
    private boolean running;
    private PlatformManager platformManager;
    private int middlePanel;
    private int score;
    private PowerUpManager powerUpManager;
    private List<PowerUp> powerUps;

    public GameEngine(GamePanel gamePanel, Player player) {
        this.gamePanel = gamePanel;
        this.middlePanel = gamePanel.getHeight() / 2;

        this.player = player;
        this.platforms = new ArrayList<>();
        this.running = false;
        this.platformManager = new PlatformManager(gamePanel, this.gamePanel.getPlayerStartingX());
        this.platforms = platformManager.getPlatforms();
        this.powerUpManager = new PowerUpManager(this.gamePanel);
        this.powerUps = this.powerUpManager.getPowerUps();
    }


    private boolean isPlayerLanding(Player player1, BasePlatform platform1) {
        Rectangle playerLegs = new Rectangle(
                player1.getX(),
                player1.getY() + player1.getHeight() - COLLISION_THRESHOLD,
                player1.getWidth() - COLLISION_THRESHOLD, COLLISION_THRESHOLD);

        Rectangle platformHead = new Rectangle(
                platform1.getX(),
                platform1.getY()
                , platform1.getWidth() - COLLISION_THRESHOLD, COLLISION_THRESHOLD);
        if (playerLegs.intersects(platformHead) && player1.getYSpeed() >= 0) {
            if (platform1.isBreakablePlatform()) {
                platform1.broke();
            }


            return true;
        } else {
            return false;
        }
    }

    private void playerJumpIfNeeded() {
        for (BasePlatform platform : this.platforms) {
            if (isPlayerLanding(this.player, platform)) {
                if (!platform.isBreakablePlatform()) {
                    this.player.jump();
                }
            }
        }
    }

    private void scrollPanel() {
        int gravity = 0;
        if (calculateDistance()) {
            gravity = -this.player.getYSpeed();
        }
        this.platformManager.scrollPlatformsDown(gravity);
        this.powerUpManager.scrollPowerUoDown(gravity);

    }

    private void scrollPlayer() {
        int yDistance = 0;
        if (calculateDistance()) {
            int feetLocation = this.player.getY() + this.player.getHeight();
            yDistance = this.middlePanel - feetLocation;

        }
        this.score += yDistance / 2;
        this.gamePanel.setScore(this.score);
        this.player.setY(yDistance);

    }

    private boolean calculateDistance() {
        if (this.player.getY() + this.player.getHeight()
                < this.middlePanel && this.player.getYSpeed() < 0) {
            return true;
        }
        return false;

    }

    public void run() {
        while (this.running) {
            this.player.updateAction();
            this.scrollPanel();
            this.scrollPlayer();
            this.platformManager.generatePlatformsIfNeeded();
            this.powerUpManager.generatePowerUpIfNeeded();
            playerJumpIfNeeded();
            this.powerUpManager.update(this.player);
            this.gamePanel.updateScore(this.score);
            this.platformManager.updateMoveablePlatforms();
            this.gamePanel.repaint();
            this.levelUp();


            try {
                this.sleep(SLEEP);
            } catch (RuntimeException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void levelUp() {
        if (this.score > LEVEL_THRESHOLD) {
            this.platformManager.makeBreakablePlatform();
        }
        if (this.score > LEVEL_THRESHOLD * 5) {
            this.platformManager.makeMoveablePlatform();
        }
        if (this.score > LEVEL_THRESHOLD * 12) {
            this.platformManager.moreDifficult();
        }
    }

        public boolean isPlayerOutOfBounds () {
            if (this.player.getY() >= this.gamePanel.getHeight()) {
                return true;
            }
            return false;
        }

        public List<BasePlatform> getPlatforms () {
            return platforms;
        }

        public void stopGame () {
            this.running = false;
        }

        public void startGame () {

            this.running = true;
        }

        public int getScore () {
            return score;
        }

        public Player getPlayer () {
            return player;
        }

    public List<PowerUp> getPowerUps() {
        return powerUps;
    }
}
