import javax.swing.*;
import java.awt.*;

public class BasePlatform {
    public static final int PLATFORM_WIDTH = 100;
    public static final int PLATFORM_HEIGHT = 20;

    private int platformX;
    private int platformY;
    private Image platformImage;
    private int platformWidth;
    private int platformHeight;
    private boolean isBreakablePlatform;
    private boolean isBroken;

    public BasePlatform(int platformX, int platformY) {
        this.platformX = platformX;
        this.platformY = platformY;
        this.platformHeight = PLATFORM_HEIGHT;
        this.platformWidth = PLATFORM_WIDTH;
        this.isBroken = false;
        this.isBreakablePlatform = false;

    }

    public void draw(Graphics g) {
        g.drawImage(platformImage, platformX, platformY, this.platformWidth,
                this.platformHeight, null);
    }

    public void setPlatformImage(Image platformImage) {
        this.platformImage = platformImage;
    }

    public int getPlatformX() {
        return platformX;
    }

    public int getPlatformY() {
        return platformY;
    }

    public int getWidth() {
        return this.platformWidth;
    }

    public int getHeight() {
        return this.platformHeight;
    }

    public void setPlatformX(int platformX) {
        this.platformX = platformX;
    }

    public void setPlatformY(int platformY) {
        this.platformY = platformY;
    }

    public void moveDown(int gravity) {
        this.platformY += gravity;

    }

    public boolean isBreakablePlatform() {
        return this.isBreakablePlatform;
    }

    public void setBreakablePlatform(boolean isBreakablePlatform) {
        this.isBreakablePlatform = isBreakablePlatform;
    }

    public void broke() {
    }

    public boolean isBroken() {
        return this.isBroken;
    }
    public void setBroken(boolean broken) {
        isBroken = broken;
    }
}
