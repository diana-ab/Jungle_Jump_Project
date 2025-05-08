package assets;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.HashMap;

public class GameImages {
    public static final String MONKEY_STANDING = "monkey_standing";
    public static final String MONKEY_JUMPING = "monkey_jumping";
    public static final String MONKEY_JUMPING_LEFT = "monkey_jumping_left";
    public static final String MONKEY_JUMPING_RIGHT = "monkey_jumping_right";
    public static final String BACKGROUND = "background";
    public static final String MENU = "menu";
    public static final String START_BUTTON = "start_game";
    public static final String BACK_BUTTON = "back_to_menu";
    public static final String GAME_OVER = "end_game";
    public static final String INSTRUCTIONS_SCREEN = "instructions_screen";
    public static final String INSTRUCTIONS_BUTTON = "instructions_button";
    public static final String PLATFORM = "platform";
    public static final String PLATFORM_BREAK = "platform_break";
    public static final String PLATFORM_LEAF = "platform_leaf";
    public static final String SCORE_BACKGROUND = "score_background";
    public static final String BANANA_POWER_UP = "banana_power_up";
    public static final String PLATFORM_MOVE = "platfprm_move";


    private HashMap<String, Image> imageMap = new HashMap<>();

    public GameImages() {
        load(MONKEY_STANDING, "images/monkey_standing.png");
        load(MONKEY_JUMPING, "images/monkey_jumping.png");
        load(MONKEY_JUMPING_LEFT, "images/monkey_jumping_left.png");
        load(MONKEY_JUMPING_RIGHT, "images/monkey_jumping_right.png");
        load(BACKGROUND, "images/background.png");
        load(MENU, "images/menu.png");
        load(START_BUTTON, "images/start_game.png");
        load(BACK_BUTTON, "images/back_to_menu.png");
        load(GAME_OVER, "images/end_game.png");
        load(INSTRUCTIONS_SCREEN, "images/jungle_jump_instructions.png");
        load(INSTRUCTIONS_BUTTON, "images/instructions.png");
        load(PLATFORM, "images/platform.png");
        load(PLATFORM_BREAK, "images/platform_break.png");
        load(PLATFORM_LEAF, "images/platform_leaf.png");
        load(SCORE_BACKGROUND, "images/wooden_tile.png");
        load(BANANA_POWER_UP, "images/wooden_tile.png");
        load(PLATFORM_MOVE, "images/movePlatfotm.png");
    }

    private void load(String key, String path) {
        URL image = getClass().getClassLoader().getResource(path);
        if (image == null) {
            System.out.println("Could not load image: " + path);
            return;
        }
        imageMap.put(key, new ImageIcon(image).getImage());
    }

    private Image get(String key) {
        return imageMap.get(key);
    }

    public Image getMonkeyStandingImage() {
        return get(MONKEY_STANDING);
    }

    public Image getMonkeyJumpingImage() {
        return get(MONKEY_JUMPING);
    }

    public Image getMonkeyJumpingLeftImage() {
        return get(MONKEY_JUMPING_LEFT);
    }

    public Image getMonkeyJumpingRightImage() {
        return get(MONKEY_JUMPING_RIGHT);
    }

    public Image getBackgroundImage() {
        return get(BACKGROUND);
    }

    public Image getMenuImage() {
        return get(MENU);
    }

    public Image getStartButtonImage() {
        return get(START_BUTTON);
    }

    public Image getBackButtonImage() {
        return get(BACK_BUTTON);
    }

    public Image getGameOverImage() {
        return get(GAME_OVER);
    }

    public Image getInstructionsScreenImage() {
        return get(INSTRUCTIONS_SCREEN);
    }

    public Image getInstructionsButtonImage() {
        return get(INSTRUCTIONS_BUTTON);
    }

    public Image getPlatformImage() {
        return get(PLATFORM);
    }

    public Image getPlatformBreakImage() {
        return get(PLATFORM_BREAK);
    }

    public Image getPlatformLeafImage() {
        return get(PLATFORM_LEAF);
    }

    public Image getScoreBackgroundImage() {
        return get(SCORE_BACKGROUND);
    }

    public Image getBananaPowerUp(){
        return get(BANANA_POWER_UP);
    }
    public Image getPlatformMove() {
        return get(PLATFORM_MOVE);
    }
}