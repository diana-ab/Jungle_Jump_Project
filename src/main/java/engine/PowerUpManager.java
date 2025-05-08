package engine;

import entities.JumpBoostPowerUp;
import entities.Player;
import entities.PowerUp;
import ui.GamePanel;
import util.LimitedList;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class PowerUpManager {
    private static final int LUCKY_CHANCE = 1;

    private List<PowerUp> powerUps;
    private GamePanel gamePanel;

    public PowerUpManager(GamePanel gamePanel) {
        this.powerUps = new LimitedList<>(LUCKY_CHANCE);
        this.gamePanel = gamePanel;
    }

    public void generatePowerUpIfNeeded(int currentY) {
        Random random = new Random();
        if (random.nextInt(100) < LUCKY_CHANCE) {
            int powerUpX = random.nextInt(this.gamePanel.getWidth());
            this.powerUps.add(new JumpBoostPowerUp(powerUpX, currentY));
        }
    }

    public void draw(Graphics g) {
        for (PowerUp p : this.powerUps) {
            p.draw(g);
        }
    }
    public void update(Player player) {
        Rectangle playerBounds = new Rectangle(player.getX(), player.getY(), player.getWidth(), player.getHeight());

        this.powerUps.removeIf(p -> {
            if (playerBounds.intersects(p.getBounds())) {
                p.applyEffect(player);
                return true;
            }
            return false;
        });
    }

    public List<PowerUp> getPowerUps() {
        return powerUps;
    }
}
