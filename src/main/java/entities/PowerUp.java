package entities;

import java.awt.*;
import java.awt.image.ImageObserver;

public abstract class PowerUp {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Image image;

    public PowerUp(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void applyEffect(Player player);

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(image, x, y, width, height, null);
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
