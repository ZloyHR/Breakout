import acm.graphics.*;
import acm.util.SoundClip;

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
        if (getY2() >= Game.GAME_Y2) {
            return true;
        }
        boolean play = false;
        if (getX1() <= Game.GAME_X1){
            setVelocityX(-getVelocityX());
            play = true;
        }
        if (getY1() <= Game.GAME_Y1) {
            setVelocityY(-getVelocityY());
            play = true;
        }
        if (getX2() >= Game.GAME_X2) {
            setVelocityX(-getVelocityX());
            play = true;
        }
        if(play) {
            SoundClip clip = new SoundClip("fx/stoneTouch.wav");
            clip.stop();
            clip.setVolume(0.5);
            clip.play();
        }
        return false;

    }

    /**
     * Returns 0 if no collision
     * Returns 1 if x collision
     * Returns 2 if y collision
     * @param changeXVelocity allow to change xVelocity
     * @param changeYVelocity allow to change yVelocity
     */
    public int checkRectCollision(GObject brick, boolean changeXVelocity, boolean changeYVelocity) {
        double x1 = brick.getBounds().getX(), x2 = x1 + brick.getBounds().getWidth();
        double y1 = brick.getBounds().getY(), y2 = y1 + brick.getBounds().getHeight();
        GRectangle brickBounds = brick.getBounds();
        boolean isLeft, isRight, isUp , isDown;
        if(brickBounds.intersects(getBounds())){
            move(-velocityX, -velocityY);
            isLeft = getX2() - 5 <= x1;
            isUp = getY2() - 5 <= y1;
            isRight = getX1() + 5 >= x2;
            isDown = getY1() + 5 >= y2;
            move(velocityX, velocityY);
        }else{
            return 0;
        }
        if (changeYVelocity && (isDown || isUp)){
            setVelocityY(-getVelocityY());
        }else
        if (changeXVelocity && (isLeft || isRight)){
            setVelocityX(-getVelocityX());
        }
        if(isLeft || isRight)return 1;else
        if(isDown || isUp)return 2;
        return 0;
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
