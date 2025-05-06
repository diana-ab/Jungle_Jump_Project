import javax.swing.*;
import java.awt.*;

public class BreakablePlatform extends BasePlatform {

    public BreakablePlatform(int platformX, int platformY) {
        super(platformX, platformY);
        this.setPlatformImage(new ImageIcon(getClass().getResource("images/platform_leaf.png")).getImage());

        this.setBreakablePlatform(true);
    }


    public void broke() {
        if (this.isBreakablePlatform()) {
            this.setBroken(true);
            this.setPlatformImage(new ImageIcon(getClass().getResource("images/platform_break.png")).getImage());
        }
        System.out.println("cenot use this mothed if not Breakable Platform ");
    }
}
