import javax.swing.*;
import java.awt.*;

public class Platform extends BasePlatform {
    public static final int PLATFORM_WIDTH = 100;
    public static final int PLATFORM_HEIGHT = 20;




    public Platform(int platformX, int platformY) {
        super(platformX, platformY);
        this.setBreakablePlatform(false);
        this.setPlatformImage(new ImageIcon(getClass().getResource("images/platform.png")).getImage());
    }
}


