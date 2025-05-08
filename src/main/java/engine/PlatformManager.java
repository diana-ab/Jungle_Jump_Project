import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlatformManager {
    public static final int PLATFORM_VERTICAL_SPACING = 100;
    public static final int LUCKY_NUMBER = 7;
    public static final int LIMIT_FOR_PLATFORM = 15;



    private List<BasePlatform> platforms;
    private GamePanel gamePanel;
    private boolean canCreateBreakable;
    private int xStartingPoint;


    public PlatformManager(GamePanel gamePanel, int xStartingPoint) {
        this.platforms = new LimitedList<>(LIMIT_FOR_PLATFORM);
        this.gamePanel = gamePanel;
        this.canCreateBreakable = false;
        this.xStartingPoint = xStartingPoint;
        this.didCreateBreakable = false;
        this.canMakeMoveablePlatform = false;
        this.canMoveFaster=false;
    }


    public void generatePlatformsIfNeeded() {
        int highestY = findHighestPlatformY();
        Random random = new Random();

        while (highestY >= this.gamePanel.getY() - Platform.PLATFORM_HEIGHT) {
            int x = generatePlatformX(highestY);
            int geasLucky = random.nextInt(LUCKY_NUMBER + 1);

            if (!overlappingPlatform(x, highestY, geasLucky)) {
                BasePlatform platform = createPlatformByChance(x, highestY, geasLucky);
                this.platforms.add(platform);
            }

            highestY -= random.nextInt(PLATFORM_VERTICAL_SPACING / 10) + PLATFORM_VERTICAL_SPACING;
        }
    }


    private int findHighestPlatformY() {
        int highestY = this.gamePanel.getHeight();
        for (BasePlatform platform : this.platforms) {
            if (platform.getY() < highestY) {
                highestY = platform.getY();
            }
        }
        return highestY;
    }

    private int generatePlatformX(int currentY) {
       int x ;
        if (currentY == this.gamePanel.getHeight()) {
            x= this.xStartingPoint;
        }
        else {
        x= new Random().nextInt(this.gamePanel.getWidth() - Platform.PLATFORM_WIDTH);
        }
        return x;
    }

    private BasePlatform createPlatformByChance(int x, int y, int luckyNumber) {
        BasePlatform platform=new BasePlatform(x,y);

        if (this.canCreateBreakable && luckyNumber == LUCKY_NUMBER
                && !this.didCreateBreakable) {
            this.didCreateBreakable = true;
            platform= new BreakablePlatform(x, y);
        }
        else if (this.canMakeMoveablePlatform && luckyNumber == LUCKY_NUMBER - 1&&
                !this.didCreateBreakable) {
            platform= new MoveablePlatform(x, y);
        }
        else {
            platform=new Platform(x,y);
        this.didCreateBreakable = false;
        }
        return platform;
    }


    public List<BasePlatform> getPlatforms() {
        return platforms;
    }

    private boolean overlappingPlatform(int x, int y, int lakyNumber) {
        int panelWidth = this.gamePanel.getWidth();
        if (lakyNumber == LUCKY_NUMBER && this.canCreateBreakable&&!this.didCreateBreakable) {
            panelWidth = panelWidth / 2;
        }
        Rectangle newPlatformRect = new Rectangle(x, y, panelWidth, Platform.PLATFORM_HEIGHT);
        for (BasePlatform platform : this.platforms) {
            Rectangle existingPlatform = new Rectangle(platform.getX()
                    , platform.getY(), panelWidth, Platform.PLATFORM_HEIGHT);
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
        this.canCreateBreakable = true;
    }
}
