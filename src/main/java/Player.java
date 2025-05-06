import javax.swing.*;
import java.awt.*;

public class Player  {
    public static final int PLAYER_HEIGHT = 80;
    public static final int PLAYER_WIDTH = 75;
    public static final int JUMP_FORCE = -42;
    public static final int GRAVITY = 3;
    public static final int MOVE_SPEED = 20;

    private int playerX;
    private int playerY;
    private int playerHeight;
    private int playerWidth;
    private int ySpeed;
    private boolean isJumping;
    private boolean movingLeft;
    private boolean movingRight;
    private Image monkeyStandingImage;
    private Image monkeyJumpingLeftImage;
    private Image monkeyJumpingRightImage;
    private Image monkeyJumpingImage;
    private Sound sound;


    public Player(int startX,int startY) {
        this.playerX = startX;
        this.playerY = startY;
        this.playerHeight= PLAYER_HEIGHT;
        this.playerWidth= PLAYER_WIDTH;
        this.ySpeed = 0;
        this.movingLeft = false;
        this.movingRight = false;
        this.isJumping = true;

        this.monkeyStandingImage = new ImageIcon(getClass().getResource("images/monkey_standing.png")).getImage();
        this.monkeyJumpingLeftImage = new ImageIcon(getClass().getResource("images/monkey_jumping_left.png")).getImage();
        this.monkeyJumpingRightImage = new ImageIcon(getClass().getResource("images/monkey_jumping_right.png")).getImage();
        this.monkeyJumpingImage = new ImageIcon(getClass().getResource("images/monkey_jumping.png")).getImage();
        this.sound=new Sound();
    }

    public void setMovingLeft(boolean moving) {
        this.movingLeft = moving;
    }
    public void setMovingRight(boolean moving) {
        this.movingRight = moving;
    }

    public void updateAction() {
        this.ySpeed += GRAVITY;
        this.playerY += this.ySpeed;

        if (this.movingRight) {
            this.playerX += MOVE_SPEED;
        }
        if (this.movingLeft) {
            this.playerX -= MOVE_SPEED;
        }

        if (this.ySpeed < 0) {

            this.isJumping = true;
        } else {
            this.isJumping = false;
        }
    }

    public void jump() {
        this.ySpeed = JUMP_FORCE;
        this.sound.playJumpSound();
    }

    public void draw(Graphics graphics) {
        Image currentImage;

        if (this.isJumping) {
            if (this.movingLeft){

            currentImage = this.monkeyJumpingLeftImage;}
            else if (this.movingRight) {
                currentImage = this.monkeyJumpingRightImage;
            }
            else { currentImage = this.monkeyJumpingImage;}
        }
        else {
            currentImage = this.monkeyStandingImage;
        }
        graphics.drawImage(currentImage, this.playerX, this.playerY, this.playerWidth, this.playerHeight, null);
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public int getYSpeed() {
        return ySpeed;
    }

    public int getPlayerWidth() {
        return playerWidth;
    }

    public int getPlayerHeight() {
        return playerHeight;
    }

    public void setPlayerY(int playerY) {
        this.playerY  +=playerY;
    }
}
