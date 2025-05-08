import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class PlatformManager {
    public static final int PLATFORM_VERTICAL_SPACING = 100;
    public static final int LAKY_NUMBER = 15;
    public static final int LIMIT_FOR_PLATFORM=20;


    private List<BasePlatform> platforms;
    private GamePanel gamePanel;
    private boolean canMakeBrekPlatform;
    private int xStartingPoint;


    public PlatformManager(GamePanel gamePanel,int xStartingPoint) {
        this.platforms = new LimitedList<>(LIMIT_FOR_PLATFORM);
        this.gamePanel = gamePanel;
        this.canMakeBrekPlatform = false;
        this.xStartingPoint=xStartingPoint;
    }

    public void generatePlatformsIfNeeded() {
        Random random = new Random();
        int highestY = this.gamePanel.getHeight();
        if (!this.platforms.isEmpty()) {
            for (BasePlatform platform : this.platforms) {
                if (platform.getPlatformY() < highestY) {
                    highestY = platform.getPlatformY();
                }
            }
        }
        while (highestY >= this.gamePanel.getY() - Platform.PLATFORM_HEIGHT) {
            int highestX = random.nextInt(this.gamePanel.getWidth() - Platform.PLATFORM_WIDTH);

            if(highestY==this.gamePanel.getHeight()){
                highestX=this.xStartingPoint;
            }

            int geesLakyNum = random.nextInt(0, LAKY_NUMBER + 1);
            if (!isOverlappingPlatform(highestX, highestY, geesLakyNum)) {

                if (this.canMakeBrekPlatform && geesLakyNum == LAKY_NUMBER) {
                    this.platforms.add(new BreakablePlatform(highestX, highestY));
                } else {
                    this.platforms.add(new Platform(highestX, highestY));
                }

            }
            highestY -= random.nextInt(PLATFORM_VERTICAL_SPACING / 10) + PLATFORM_VERTICAL_SPACING;
        }
    }

    public List<BasePlatform> getPlatforms() {
        return platforms;
    }

    private boolean isOverlappingPlatform(int x, int y, int lakyNumber) {
        int panelWidth = this.gamePanel.getWidth();

        if (lakyNumber == LAKY_NUMBER && this.canMakeBrekPlatform) {
            panelWidth = panelWidth / 2;
        }
        Rectangle newPlatformRect = new Rectangle(x, y, panelWidth, Platform.PLATFORM_HEIGHT);
        for (BasePlatform platform : this.platforms) {
            Rectangle existingPlatform = new Rectangle(platform.getPlatformX()
                    , platform.getPlatformY(), panelWidth, Platform.PLATFORM_HEIGHT);
            if (newPlatformRect.intersects(existingPlatform)) {
                return true;
            }
        }
        return false;
    }

    public void scrollPlatformsDown(int gravity) {
        for (BasePlatform platform : this.platforms) {
            platform.moveDown(gravity);
        }
    }

    public void makeBreakablePlatform() {
        this.canMakeBrekPlatform = true;
    }
}
