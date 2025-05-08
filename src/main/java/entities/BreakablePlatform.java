package entities;

import assets.GameImages;


public class BreakablePlatform extends BasePlatform {
    GameImages gameImages;

    public BreakablePlatform(int platformX, int platformY) {
        super(platformX, platformY);
        this.gameImages = new GameImages();
        this.setPlatformImage(this.gameImages.getPlatformLeafImage());

        this.setBreakablePlatform(true);
    }


    public void broke() {
        if (this.isBreakablePlatform()) {
            this.setBroken(true);
            this.setPlatformImage(this.gameImages.getPlatformBreakImage());
        }
    }
}
