package entities;

import assets.GameImages;
import assets.Sound;


public class BreakablePlatform extends BasePlatform {
    private GameImages gameImages;
    private Sound sound;

    public BreakablePlatform(int platformX, int platformY) {
        super(platformX, platformY);
        this.gameImages = new GameImages();
        this.setPlatformImage(this.gameImages.getPlatformLeafImage());
        this.sound=new Sound();

        this.setBreakablePlatform(true);
    }


    public void broke() {
        if (this.isBreakablePlatform()) {
            this.setBroken(true);
            this.sound.playBreak();
            this.setPlatformImage(this.gameImages.getPlatformBreakImage());
        }
    }
}
