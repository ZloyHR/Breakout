import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class Main extends GraphicsProgram {

    /** Width and height of application window in pixels */
    public static final int APPLICATION_WIDTH = 1200;
    public static final int APPLICATION_HEIGHT = 600;

    public static double GAME_X1 = 0;
    public static double GAME_X2;
    public static double GAME_Y1 = 60;
    public static double GAME_Y2;

    public static RandomGenerator gen = new RandomGenerator();

    /** Dimensions of the paddle */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;

    /** Separation between bricks */
    public static double BRICK_SEP = 4;

    public static double BRICK_IN_ROW = 15;

    /** Width of a brick */
    public static double BRICK_WIDTH = 0;

    /** Height of a brick */
    public static double BRICK_HEIGHT = 25;

    /** Radius of the ball in pixels */
    private static final int BALL_RADIUS = 10;

    /** Number of lives */
    private static final int LIVES = 3;

    private static final int FRAME_UPDATE = 10;

    Ball ball;
    Platform plat = new Platform("img/Plat.png");
    Score score = new Score("Score : ");
    Time time = new Time("");
    Lives lives = new Lives("");
    Font font;

    private int brickCount = 0;

    GRect statisticBox;
    GImage back;

    /* Method: run() */
    /** Runs the Breakout program. */
    @Override
    public void run() {
        /* You fill this in, along with any subsidiary methods */
        font = new Font(Font.SERIF,Font.PLAIN,24);

        this.setSize(APPLICATION_WIDTH,APPLICATION_HEIGHT);
        GAME_X2 = getWidth();
        GAME_Y2 = getHeight();

        back = new GImage("img/Back.png");
        back.scale(getWidth() / back.getWidth());
        add(back);

        BRICK_WIDTH = (GAME_X2 - GAME_X1 - BRICK_SEP * (BRICK_IN_ROW - 1)) / BRICK_IN_ROW;

        statisticBox = new GRect(GAME_X1,0,getWidth(),GAME_Y1);
        statisticBox.setFillColor(new Color(53,77,85));
        statisticBox.setFilled(true);
        add(statisticBox);

        score.setFont(font.deriveFont(Font.BOLD,24.0f));
        score.setLocation(statisticBox.getWidth() / 2 - score.getWidth() / 2,
                statisticBox.getHeight() / 2 + score.getHeight() / 4);
        add(score);

        GImage heart = new GImage("img/Heart.png");
        heart.scale(0.1);
        heart.setLocation(GAME_X1,statisticBox.getHeight() / 2 - heart.getHeight() / 2);
        add(heart);

        lives.setFont(font.deriveFont(Font.BOLD,24.0f));
        lives.setLocation(GAME_X1 + heart.getWidth() + 5,
                statisticBox.getHeight() / 2 + lives.getHeight() / 4);
        lives.setValue(LIVES);
        add(lives);

        time.setFont(font.deriveFont(Font.BOLD,24.0f));
        time.setValue(0);
        time.setLocation(GAME_X2 - time.getWidth() - 10,
                statisticBox.getHeight() / 2 + time.getHeight() / 4);
        add(time);

        GImage timer = new GImage("img/Time.png");
        timer.scale(0.1);
        timer.setLocation(time.getX() - timer.getWidth() - 5,statisticBox.getHeight() / 2 - timer.getHeight() / 2);
        add(timer);


        addMouseListeners();

        plat.setLocation(getWidth() / 2 - plat.getWidth() / 2,getHeight() - 3 * plat.getHeight());
        add(plat);

        BrickGenerator gen = new Level2();
        brickCount = gen.generate(this.getGCanvas());
        ball = new Ball((GAME_X2 + GAME_X1)/ 2 - BALL_RADIUS,plat.getY1() - 3 * BALL_RADIUS,BALL_RADIUS,BALL_RADIUS);
        add(ball);
        ball.reSpawn(5);

        plat.sendToFront();

        gameCycle();
    }

    private void gameCycle() {
        while (!gameOver()){
            ball.moveByVelocity();
            if(ball.checkWallCollision()){
                lives.addToValue(-1);
                ball.reSpawn(5);
            };
            checkBrick();
            checkPlat();
            time.addToValue(1.75 * FRAME_UPDATE);
            pause(FRAME_UPDATE);
        }
    }

    private boolean gameOver(){
        return !lives.isLive() || brickCount <= 0;
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        plat.setLocation(mouseEvent.getX() - plat.getWidth() / 2,plat.getY());
    }

    /**Figure situation than ball is colliding with brick*/
    private void checkBrick(){
        for(double addX = 0;addX <= ball.getBounds().getWidth(); addX += ball.getBounds().getWidth()){
            for(double addY = 0;addY <= ball.getBounds().getWidth(); addY += ball.getBounds().getWidth()){
                GObject elementAt = getElementAt(ball.getX1() + addX, ball.getY1() + addY);
                if(!isBrick(elementAt))continue;
                if(ball.checkRectCollision(elementAt,true)){
                    removeBrick(elementAt);
                    return;
                }
            }
        }
    }

    private void removeBrick(GObject brick){
        remove(brick);
        brickCount--;
        score.addToValue(100);
    }

    /**Figure situation than ball is colliding with platform*/
    private void checkPlat() {
        for(double addX = 0;addX <= ball.getBounds().getWidth(); addX += ball.getBounds().getWidth()){
            for(double addY = 0;addY <= ball.getBounds().getWidth(); addY += ball.getBounds().getWidth()){
                GObject elementAt = getElementAt(ball.getX1() + addX, ball.getY1() + addY);
                if(elementAt == plat && ball.checkPlatCollision(elementAt)){
                    plat.bounce(ball);
                    return;
                }
            }
        }
    }

    private boolean isBrick(GObject obj){
        return obj != back && obj != ball && obj != statisticBox && obj != plat && obj != null;
    }

}