package entities;

import java.awt.*;
import java.awt.image.ImageObserver;

public abstract class PowerUp extends GameObject {

    private Image image;
    private boolean used;

    public PowerUp(int x, int y, int width, int height) {
        super(x,y,width,height);

    }

    public abstract void applyEffect(Player player);

    public Rectangle getBounds() {
        return new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    public void draw(Graphics graphics) {
        super.draw(graphics,this.image);
    }

    public void setImage(Image image) {
        this.image = image;
    }
    public void setY(int platformY) {
        int y = this.getY();
        y += platformY;
        super.setY(y);
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
