import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GOval;

import java.awt.*;

public class Ball extends GImage {
    private double velocityX;
    private double velocityY;

    private double startX;
    private double startY;

    private int cnt = 1;

    public Ball(String s) {
        super(s);
        startX = startY = 0;
    }

    public Ball(String s, double v, double v1) {
        super(s, v, v1);
        startX = v;
        startY = v1;
    }

    /** Move ball by it's velocity*/
    public void moveByVelocity() {
        move(velocityX, velocityY);
        setImage("img/Blade" + cnt + ".png");
        cnt++;
        if(cnt == 5)cnt = 1;
    }

    /**Returns xVelocity of ball*/
    public double getVelocityX() {
        return velocityX;
    }

    /**Sets xVelocity of ball*/
    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    /**Returns yVelocity of ball*/
    public double getVelocityY() {
        return velocityY;
    }

    /**Sets yVelocity of ball*/
    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    /** Left side of object*/
    public double getX1(){ return  getBounds().getX(); }
    /** Right side of object*/
    public double getX2(){ return getBounds().getX() + getBounds().getWidth(); }
    /** Up side of object*/
    public double getY1(){ return getBounds().getY(); }
    /** Down side of object*/
    public double getY2(){ return getBounds().getY() + getBounds().getHeight(); }

    /** Center x coordinate of object*/
    public double getCenterX(){
        return (getX1() + getX2()) / 2;
    }
    /** Center y coordinate of object*/
    public double getCenterY(){
        return (getY1() + getY2()) / 2;
    }

    /** Returns magnitude of ball velocity*/
    public double getVelocity() {
        return Math.sqrt(velocityX * velocityX + velocityY * velocityY);
    }

    /**Scale ball velocity by double*/
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
        if (getY2() >= Main.GAME_Y2) {
            return true;
        }
        if (getX1() <= Main.GAME_X1)
            setVelocityX(-getVelocityX());
        if (getY1() <= Main.GAME_Y1)
            setVelocityY(-getVelocityY());
        if (getX2() >= Main.GAME_X2)
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

    public boolean checkPlatCollision(GObject plat) {
        double x1 = plat.getBounds().getX(), x2 = x1 + plat.getBounds().getWidth();
        double y1 = plat.getBounds().getY(), y2 = y1 + plat.getBounds().getHeight();
        move(-velocityX, -velocityY);
        boolean isUp = getCenterY() <= y1;
        move(velocityX, velocityY);
        return isUp;
    }

    public void reSpawn(double magnitude){
        randomVelocity(magnitude);
        setLocation(startX,startY);
        pause(1000);
    }

    public void randomVelocity(double magnitude){
        setVelocityX((Main.gen.nextBoolean() ? 1 : -1) * Main.gen.nextDouble(1,magnitude));
        setVelocityY(-Math.sqrt(magnitude * magnitude - velocityX * velocityX));
    }

}
