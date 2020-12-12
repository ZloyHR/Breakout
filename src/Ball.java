import acm.graphics.GObject;
import acm.graphics.GOval;

import java.awt.*;

public class Ball extends GOval {
    private double velocityX;
    private double velocityY;

    public Ball(double v, double v1) {
        super(v, v1);
    }

    public Ball(double v, double v1, double v2, double v3) {
        super(v, v1, v2, v3);
        velocityX = 2;
        velocityY = 4;
    }

    public void moveByVelocity() {
        move(velocityX, velocityY);
    }

    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public double getX1() {
        return getBounds().getX();
    }

    public double getX2() {
        return getBounds().getX() + getBounds().getWidth();
    }

    public double getY1() {
        return getBounds().getY();
    }

    public double getY2() {
        return getBounds().getY() + getBounds().getHeight();
    }

    public double getCenterX() {
        return (getX1() + getX2()) / 2;
    }

    public double getCenterY() {
        return (getY1() + getY2()) / 2;
    }

    public double getVelocity() {
        return Math.sqrt(velocityX * velocityX + velocityY * velocityY);
    }

    public void scaleVelocity(double scale) {
        scale = Math.sqrt(scale);
        velocityX *= scale;
        velocityY *= scale;
    }

    /**
     * Returns true if ball under the window
     * Returns false if ball collide with window border and change velocity to keep ball inside window
     */
    public boolean checkWallCollision() {
        if (getY2() >= Main.HEIGHT) {
            setVelocityY(-getVelocityY());
            return true;
        }
        if (getX1() <= 0)
            setVelocityX(-getVelocityX());
        if (getY1() <= 0)
            setVelocityY(-getVelocityY());
        if (getX2() >= Main.WIDTH)
            setVelocityX(-getVelocityX());
        return false;

    }

    /**
     * Returns false if ball collide with brick and change velocity to jump ball out after brick collision
     */
    public boolean checkRectCollision(GObject brick, boolean changeVelocity) {
        double x1 = brick.getBounds().getX(), x2 = x1 + brick.getBounds().getWidth();
        double y1 = brick.getBounds().getY(), y2 = y1 + brick.getBounds().getHeight();
        move(-velocityX, -velocityY);
        boolean isLeft = getCenterX() <= x1, isRight = getCenterX() >= x2, isUp = getCenterY() <= y1, isDown = getCenterY() >= y2;
        move(velocityX, velocityY);
        if (changeVelocity) {
            if (isLeft || isRight) setVelocityX(-getVelocityX());
            else if (isUp || isDown) setVelocityY(-getVelocityY());
        }
        return isLeft || isRight || isUp || isDown;
    }

}
