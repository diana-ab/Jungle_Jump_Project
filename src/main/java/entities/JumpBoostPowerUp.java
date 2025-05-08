package entities;

import assets.GameImages;
import ui.GamePanel;
import util.LimitedList;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class JumpBoostPowerUp extends PowerUp {
    public static final int BANANA_WIDTH = 30;
    public static final int BANANA_HEIGHT = 30;
    public static final int SLEEP = 5500;

    private GameImages gameImages;
    private boolean used;

    public JumpBoostPowerUp(int x, int y) {
        super(x, y, BANANA_WIDTH, BANANA_HEIGHT);
        this.gameImages = new GameImages();
       this.setImage( this.gameImages.getBananaPowerUp());
        this.used = false;



    }


    @Override
    public void applyEffect(Player player) {
        if (!used) {
            int originalSpeed = player.getYSpeed();
            player.setYSpeed(originalSpeed * 2);
            this.used = true;
            new Thread(() -> {
                try {
                    Thread.sleep(SLEEP);
                    player.setYSpeed(originalSpeed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
