package entities;

import java.awt.*;

public class GameObject {
    private int x;
    private int y;
    private int width;
    private int height;


    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void draw(Graphics g, Image image) {
        g.drawImage(image, this.x, this.y, this.width,
                this.height, null);
    }
    public void moveDown(int gravity) {
        this.setY(gravity);
    }
}
