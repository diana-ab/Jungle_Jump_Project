package entities;

import assets.GameImages;

public class JumpBoostPowerUp extends PowerUp {
    public static final int BANANA_WIDTH = 30;
    public static final int BANANA_HEIGHT = 30;
    public static final int SLEEP = 5500;

    private GameImages gameImages;


    public JumpBoostPowerUp(int x, int y) {
        super(x, y, BANANA_WIDTH, BANANA_HEIGHT);
        this.gameImages = new GameImages();
       this.setImage( this.gameImages.getBananaPowerUp());
        this.setUsed(false);



    }


    @Override
    public void applyEffect(Player player) {
            player.superJump();
            new Thread(() -> {
                try {
                    Thread.sleep(SLEEP);
                    player.stopSuperJump();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

    }
}
