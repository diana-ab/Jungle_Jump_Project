package entities;

import assets.GameImages;
import assets.Movement;
import assets.Sound;

import java.awt.*;

public class Player extends GameObject implements Movement {
    public static final int PLAYER_HEIGHT = 80;
    public static final int PLAYER_WIDTH = 75;
    public static final int JUMP_FORCE = -42;
    public static final int GRAVITY = 3;
    public static final int MOVE_SPEED = 20;


    private int ySpeed;
    private boolean isJumping;
    private boolean movingLeft;
    private boolean movingRight;
    private GameImages gameImages;
    private Sound sound;
    private int jumpForce;
    private boolean ifSuperJump;


    public Player(int startX, int startY) {
        super(startX, startY, PLAYER_WIDTH, PLAYER_HEIGHT);
        this.gameImages = new GameImages();
        this.ySpeed = 0;
        this.movingLeft = false;
        this.movingRight = false;
        this.isJumping = true;
        this.sound = new Sound();
        this.jumpForce = JUMP_FORCE;
        this.ifSuperJump = false;
    }

    public void setIsMovingLeft(boolean moving) {
        this.movingLeft = moving;
    }

    public void setIsMovingRight(boolean moving) {
        this.movingRight = moving;
    }

    public void updateAction() {
        this.ySpeed += GRAVITY;
        this.setY(this.ySpeed);
        if (this.movingRight) {
            moveRight();
        }
        if (this.movingLeft) {
            moveLeft();
        }
        if (this.ySpeed < 0) {

            this.isJumping = true;
        } else {
            this.isJumping = false;
        }
    }

    public void jump() {
        this.ySpeed = this.jumpForce;
        this.sound.playJumpSound();
    }

    public void draw(Graphics graphics) {
        Image currentImage;
        if (this.isJumping) {
            if (this.ifSuperJump) {
                currentImage = gameImages.getSuperJump();
            } else {
                if (this.movingLeft) {
                    currentImage = gameImages.getMonkeyJumpingLeftImage();
                } else if (this.movingRight) {
                    currentImage = gameImages.getMonkeyJumpingRightImage();
                } else {
                    currentImage = gameImages.getMonkeyJumpingImage();
                }
            }
        } else {
            currentImage = gameImages.getMonkeyStandingImage();
        }
        super.draw(graphics, currentImage);
    }

    public int getYSpeed() {
        return ySpeed;
    }

    public void setY(int playerY) {
        int y = this.getY();
        y += playerY;
        super.setY(y);
    }

    @Override
    public void moveLeft() {
        int x = this.getX();
        x -= MOVE_SPEED;
        this.setX(x);

    }

    @Override
    public void moveRight() {
        int x = this.getX();
        x += MOVE_SPEED;
        this.setX(x);

    }


    public boolean isIfSuperJump() {
        return ifSuperJump;
    }

    private void setIfSuperJump(boolean ifSuperJump) {
        this.ifSuperJump = ifSuperJump;
    }

    public void superJump() {
        this.sound.playPowerUp();
        this.ySpeed = this.jumpForce * 4;
        setIfSuperJump(true);
    }

    public void stopSuperJump() {
        this.sound.stopPlayPowerUp();
        this.jumpForce = JUMP_FORCE;
        setIfSuperJump(false);
    }
}
