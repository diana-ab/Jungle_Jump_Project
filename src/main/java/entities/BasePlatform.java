import javax.swing.*;
import java.awt.*;

public class BasePlatform extends GameObject {
    public static final int PLATFORM_WIDTH = 100;
    public static final int PLATFORM_HEIGHT = 20;


    private Image platformImage;
    private boolean isBreakablePlatform;
    private boolean isBroken;

    public BasePlatform(int platformX, int platformY) {
        super(platformX, platformY, PLATFORM_WIDTH, PLATFORM_HEIGHT);
        this.isBroken = false;
        this.isBreakablePlatform = false;
        this.isMoveable=false;
    }

    public void draw(Graphics g) {
        super.draw(g, this.platformImage);
    }

    public void setPlatformImage(Image platformImage) {
        this.platformImage = platformImage;
    }

    public void setY(int platformY) {
        int y = this.getY();
        y += platformY;
        super.setY(y);
    }


    public void moveDown(int gravity) {
        this.setY(gravity);
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
