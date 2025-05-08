package entities;

import assets.GameImages;
import assets.Movement;

import java.util.Random;

public class MoveablePlatform extends Platform implements Movement {
    private static final int MOVE_SPEED = 6;

    private int speed;
    private boolean movingLeft;
    private boolean movingRight;
    private boolean canMoveFasterActiveitOnes;
    private GameImages gameImages;

    public MoveablePlatform(int platformX, int platformY) {
        super(platformX, platformY);
        this.speed = MOVE_SPEED;
        this.setMoveable(true);
        Random random = new Random();
        boolean startRight = random.nextBoolean();
        this.movingRight = startRight;
        this.movingLeft = !startRight;
        this.canMoveFasterActiveitOnes=false;
        gameImages=new GameImages();
        this.setPlatformImage(gameImages.getPlatformMove());

    }
    public void startMoving() {
        if (this.isMovingLeft()) {
            this.moveLeft();
        }
        if (this.isMovingRight()) {
            this.moveRight();
        }
    }

    public void moveLeft() {
        int x = this.getX();
        x -= this.speed;
        this.setX(x);

    }

    @Override
    public void moveRight() {
        int x = this.getX();
        x += this.speed;
        this.setX(x);

    }
    public boolean isMovingLeft() {
        return movingLeft;
    }
    public boolean isMovingRight() {
        return movingRight;
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }
    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;}

    public void moreSpeed(){
        this.speed=this.speed*2;
        this.canMoveFasterActiveitOnes=true;

    }
    public boolean isMovingFaster(){
        return this.canMoveFasterActiveitOnes;
    }
}
