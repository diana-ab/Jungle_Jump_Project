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
    private static final int LUCKY_CHANCE = 2;
    private static final int CHANCE = 1000;

    private List<PowerUp> powerUps;
    private GamePanel gamePanel;
    private boolean used;

    public PowerUpManager(GamePanel gamePanel) {
        this.powerUps = new LimitedList<>(LUCKY_CHANCE);
        this.gamePanel = gamePanel;
        this.used = false;
    }

    public void generatePowerUpIfNeeded() {
        if (this.powerUps.size() == LUCKY_CHANCE) {
            if (removePowerUp()) {
                this.generatePowerUp();
            }
        } else {
            Random random = new Random();
            if (random.nextInt(CHANCE) < LUCKY_CHANCE) {
                this.generatePowerUp();
            }
        }

    }

    private void generatePowerUp() {
        Random random = new Random();
        int powerUpX = random.nextInt(this.gamePanel.getWidth());
        JumpBoostPowerUp power =
                new JumpBoostPowerUp(powerUpX, this.gamePanel.getY() - JumpBoostPowerUp.BANANA_HEIGHT);
        this.powerUps.add(power);

    }

    private boolean removePowerUp() {
        if (this.powerUps.get(0).getY() > this.gamePanel.getHeight() + this.powerUps.get(0).getHeight()) {
            this.powerUps.remove(0);
            return true;
        }
        return false;
    }


    public void update(Player player) {
        Rectangle playerBounds = new Rectangle(player.getX(), player.getY(), player.getWidth(), player.getHeight());

        this.powerUps.removeIf(p -> {
            if (playerBounds.intersects(p.getBounds()) && !player.isIfSuperJump()) {
                p.applyEffect(player);
                return true;
            }
            return false;
        });
    }

    public List<PowerUp> getPowerUps() {
        return powerUps;
    }

    public void scrollPowerUoDown(int gravity) {
        for (PowerUp power : this.powerUps) {
            power.moveDown(gravity);
        }
    }
}
