import acm.graphics.GCanvas;
import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.util.SoundClip;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Game {
    /** Left-Up coordinate of game field */
    public static double GAME_X1 = 0;
    /** Right-Up coordinate of game field */
    public static double GAME_X2;
    /** Left-Down coordinate of game field */
    public static double GAME_Y1 = 60;
    /** Right-Down coordinate of game field */
    public static double GAME_Y2;

    /** Separation between bricks */
    public static double BRICK_SEP = 4;

    /** Amount of bricks in row*/
    public static double BRICK_IN_ROW = 15;

    /** Width of a brick */
    public static double BRICK_WIDTH = 0;

    /** Height of a brick */
    public static double BRICK_HEIGHT = 25;

    /** Number of lives */
    private static final int LIVES = 3;

    /** Ball velocity */
    private static final double BALL_VELOCITY = 2.5;

    /**Ball object*/
    Ball ball;

    /**Platform object*/
    Platform plat = new Platform("img/Platform.png");

    /**Score object*/
    static Score score = new Score("Score : ",0);

    /**Time object*/
    static Time time = new Time("",0);

    /**Live object*/
    static Lives lives = new Lives("",LIVES);

    /**Amount of bricks that left */
    private int brickCount = 0;

    /**Rect holder of all statistics*/
    GRect statisticBox;

    /**Background image*/
    GImage back;

    /**Game canvas*/
    GCanvas gameCanvas;

    /** Returns current level class*/
    public BrickGenerator getLevel() {
        return level;
    }

    /** Sets current level as given*/
    public void setLevel(BrickGenerator level) {
        this.level = level;
    }

    /**Current level*/
    BrickGenerator level;

    /**Constructor that creates class with given canvas*/
    public Game(GCanvas canvas) {
        canvas.removeAll();
        gameCanvas = canvas;
    }

    /**Resets all stats components*/
    public void resetGame(){
        score = new Score("Score : ",0);
        time = new Time("",0);
        lives = new Lives("",LIVES);
    }

    /** Runs the Breakout program. */
    public boolean run() {
        /* You fill this in, along with any subsidiary methods */


        gameCanvas.setSize(Main.APPLICATION_WIDTH,Main.APPLICATION_HEIGHT);

        back = new GImage("img/Back.png");
        back.scale(gameCanvas.getWidth() / back.getWidth(),gameCanvas.getHeight() / back.getHeight());
        gameCanvas.add(back);


        statisticBox = new GRect(GAME_X1,0,gameCanvas.getWidth(),GAME_Y1);
        statisticBox.setFillColor(new Color(53,77,85));
        statisticBox.setFilled(true);
        gameCanvas.add(statisticBox);

        score.setFont(Main.font.deriveFont(Font.BOLD,24.0f));
        score.setLocation(statisticBox.getWidth() / 2 - score.getWidth() / 2,
                statisticBox.getHeight() / 2 + score.getHeight() / 4);
        gameCanvas.add(score);

        GImage heart = new GImage("img/Heart.png");
        heart.scale(0.1);
        heart.setLocation(GAME_X1,statisticBox.getHeight() / 2 - heart.getHeight() / 2);
        gameCanvas.add(heart);

        lives.setFont(Main.font.deriveFont(Font.BOLD,24.0f));
        lives.setLocation(GAME_X1 + heart.getWidth() + 5,
                statisticBox.getHeight() / 2 + lives.getHeight() / 4);
        gameCanvas.add(lives);

        time.setFont(Main.font.deriveFont(Font.BOLD,24.0f));
        time.setLocation(GAME_X2 - time.getWidth() - 10,
                statisticBox.getHeight() / 2 + time.getHeight() / 4);
        gameCanvas.add(time);

        GImage timer = new GImage("img/Time.png");
        timer.scale(0.1);
        timer.setLocation(time.getX() - timer.getWidth() - 5,statisticBox.getHeight() / 2 - timer.getHeight() / 2);
        gameCanvas.add(timer);


        plat.scale(0.35);
        plat.setLocation(gameCanvas.getWidth() / 2 - plat.getWidth() / 2,gameCanvas.getHeight() - 3 * plat.getHeight());
        gameCanvas.add(plat);


        BrickGenerator x = level;
        brickCount = x.generate(gameCanvas);
        ball = new Ball("img/Ball.gif",(GAME_X2 + GAME_X1)/ 2 - 30,plat.getY1() - 60);
        ball.scale(0.2);
        gameCanvas.add(ball);
        ball.reSpawn(BALL_VELOCITY);

        plat.sendToFront();

        return gameCycle();
    }

    /**Returns true if player wins level and false if opposite*/
    private boolean gameCycle() {
        while (!gameOver()){
            ball.moveByVelocity();
            if(ball.checkWallCollision()){
                lives.addToValue(-1);
                if(lives.isLive())ball.reSpawn(BALL_VELOCITY);
            };
            checkBrick();
            checkPlat();
            time.addToValue(1.75 * Main.FRAME_UPDATE);
            Main.instance.pause(Main.FRAME_UPDATE);
        }
        ball.setLocation(5000,5000);
        return brickCount <= 0;
    }

    /**Returns true if game is over*/
    private boolean gameOver(){
        return !lives.isLive() || brickCount <= 0;
    }

    /**Move platform if mouse moved*/
    public void mouseMoved(MouseEvent mouseEvent) {
        double x = mouseEvent.getX() - plat.getWidth() / 2;
        if(x < GAME_X1)x = GAME_X1;
        if(x + plat.getWidth() > GAME_X2)x = GAME_X2- plat.getWidth();
        plat.setLocation(x,plat.getY());
    }

    /**Figure situation than ball is colliding with brick*/
    private void checkBrick(){
        if(checkBrick(ball.getX1(),ball.getCenterY()))return;
        if(checkBrick(ball.getX2(),ball.getCenterY()))return;
        if(checkBrick(ball.getCenterX(),ball.getY1()))return;
        if(checkBrick(ball.getCenterX(),ball.getY2()))return;
        if(checkBrick(ball.getX1(),ball.getY1()))return;
        if(checkBrick(ball.getX1(),ball.getY2()))return;
        if(checkBrick(ball.getX2(),ball.getY1()))return;
        if(checkBrick(ball.getX2(),ball.getY2()))return;
    }

    /**Returns true if brick was removed*/
    private boolean checkBrick(double x,double y){
        GObject elementAt = gameCanvas.getElementAt(x, y);
        if(!isBrick(elementAt))return false;
        int val = ball.checkRectCollision(elementAt,true,true);
        if(val != 0){
            removeBrick(elementAt);
            return true;
        }
        return false;
    }

    /**Removes brick from canvas with special sound*/
    private void removeBrick(GObject brick){
        long y = Math.round((brick.getBounds().getY() - GAME_Y1) / BRICK_HEIGHT);
        gameCanvas.remove(brick);
        SoundClip clip = new SoundClip("fx/woodTouch.wav");
        clip.setVolume(0.5);
        clip.play();
        brickCount--;
        y = (10 - y) / 2;
        score.addToValue(y * 100);
    }

    /**Figure situation than ball is colliding with platform*/
    private void checkPlat() {
        for(double addX = 0;addX <= ball.getBounds().getWidth(); addX += ball.getBounds().getWidth()){
            for(double addY = 0;addY <= ball.getBounds().getWidth(); addY += ball.getBounds().getWidth()){
                GObject elementAt = gameCanvas.getElementAt(ball.getX1() + addX, ball.getY1() + addY);
                if(elementAt == plat && ball.checkPlatCollision(elementAt)){
                    SoundClip clip = new SoundClip("fx/snow.wav");
                    clip.setVolume(0.4);
                    clip.play();
                    plat.bounce(ball);
                    return;
                }
            }
        }
    }

    /**Returns true if this object is Brick*/
    private boolean isBrick(GObject obj){
        return obj != null && Math.abs(obj.getBounds().getWidth() - BRICK_WIDTH) < 1;
        //return obj != back && obj != ball && obj != statisticBox && obj != plat && obj != null;
    }

}
