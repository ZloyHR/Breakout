import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

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

    /** Dimensions of the paddle */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;

    /** Offset of the paddle up from the bottom */
    private static final int PADDLE_Y_OFFSET = 530;

    /** Number of bricks per row */
    private static final int NBRICKS_PER_ROW = 10;

    /** Number of rows of bricks */
    private static final int NBRICK_ROWS = 10;

    /** Separation between bricks */
    private static final int BRICK_SEP = 4;

    /** Width of a brick */
    private static final int BRICK_WIDTH = 0;

    /** Height of a brick */
    private static final int BRICK_HEIGHT = 8;

    /** Radius of the ball in pixels */
    private static final int BALL_RADIUS = 10;

    /** Offset of the top brick row from the top */
    private static final int BRICK_Y_OFFSET = 70;

    /** Number of lives */
    private static final int LIVES = 3;

    private static final int FRAME_UPDATE = 10;

    Ball ball = new Ball(0,520,BALL_RADIUS,BALL_RADIUS);
    Platform plat = new Platform("img/Plat.png");
    Score score = new Score("Score : ");
    Time time = new Time("Time pass : ");
    Lives lives = new Lives("Lives left : ");
    Font font;

    GRect statisticBox;

    /* Method: run() */
    /** Runs the Breakout program. */
    @Override
    public void run() {
        /* You fill this in, along with any subsidiary methods */
        font = new Font(Font.SERIF,Font.PLAIN,24);

        this.setSize(APPLICATION_WIDTH,APPLICATION_HEIGHT);
        GAME_X2 = getWidth();
        GAME_Y2 = getHeight();

        statisticBox = new GRect(GAME_X1,0,getWidth(),GAME_Y1);
        statisticBox.setFillColor(Color.RED);
        statisticBox.setFilled(true);
        add(statisticBox);

        score.setFont(font.deriveFont(Font.BOLD,24.0f));
        score.setLocation(statisticBox.getWidth() / 2 - score.getWidth() / 2,
                statisticBox.getHeight() / 2);
        add(score);

        lives.setFont(font.deriveFont(Font.BOLD,24.0f));
        lives.setLocation(GAME_X1,
                statisticBox.getHeight() / 2);
        lives.setValue(LIVES);
        add(lives);

        time.setFont(font.deriveFont(Font.BOLD,24.0f));
        time.setValue(0);
        time.setLocation(GAME_X2 - time.getWidth() - 10,
                statisticBox.getHeight() / 2);
        add(time);

        addMouseListeners();

        add(ball);

        plat.setLocation(getWidth() / 2 - plat.getWidth() / 2,getHeight() - 3 * plat.getHeight());
        add(plat);

        createBricks();

        while (true){
            ball.moveByVelocity();
            ball.checkWallCollision();
            checkBrick();
            checkPlat();
            time.addToValue(FRAME_UPDATE);
            pause(FRAME_UPDATE);
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        plat.setLocation(mouseEvent.getX() - plat.getWidth() / 2,plat.getY());
    }

    private void createBricks(){
        double width = getWidth() / 11.0 - 1;
        double height = 25;
        double x = 0,y = BRICK_Y_OFFSET;
        for(int i = 0;i <= 10; ++i){
            x = -width;
            for(int j = 0;j <= 10; ++j){
                x += width + 1;
                if(j == 3 || j == 7)continue;;
                Brick brick = new Brick(x,y,width,height);
                add(brick);
            }
            y += height + 1;
        }
    }

    /**Figure situation than ball is colliding with brick*/
    private void checkBrick(){
        for(double addX = 0;addX <= ball.getBounds().getWidth(); addX += ball.getBounds().getWidth()){
            for(double addY = 0;addY <= ball.getBounds().getWidth(); addY += ball.getBounds().getWidth()){
                GObject elementAt = getElementAt(ball.getX1() + addX, ball.getY1() + addY);
                if(!isBrick(elementAt))continue;
                if(ball.checkRectCollision(elementAt,true)){
                    remove(elementAt);
                    score.addToValue(100);
                    return;
                }
            }
        }
    }

    /**Figure situation than ball is colliding with platform*/
    private void checkPlat() {
        for(double addX = 0;addX <= ball.getBounds().getWidth(); addX += ball.getBounds().getWidth()){
            for(double addY = 0;addY <= ball.getBounds().getWidth(); addY += ball.getBounds().getWidth()){
                GObject elementAt = getElementAt(ball.getX1() + addX, ball.getY1() + addY);
                if(elementAt == plat && ball.checkRectCollision(elementAt,false)){
                    plat.bounce(ball);
                    return;
                }
            }
        }
    }

    private boolean isBrick(GObject obj){
        return obj != ball && obj != statisticBox && obj != plat && obj != null;
    }

}