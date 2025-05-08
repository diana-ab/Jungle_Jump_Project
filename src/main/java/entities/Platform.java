package entities;

import assets.GameImages;

public class Platform extends BasePlatform {
    public static final int PLATFORM_WIDTH = 100;
    public static final int PLATFORM_HEIGHT = 20;

    private GameImages gameImages;


    public Platform(int platformX, int platformY) {
        super(platformX, platformY);
        this.gameImages = new GameImages();
        this.setBreakablePlatform(false);
        this.setPlatformImage(this.gameImages.getPlatformImage());
    }
}


