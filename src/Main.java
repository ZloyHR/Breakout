import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
import acm.util.SoundClip;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class Main extends GraphicsProgram {

    /** Width and height of application window in pixels */
    public static final int APPLICATION_WIDTH = 1200;
    public static final int APPLICATION_HEIGHT = 600;

    public static RandomGenerator gen = new RandomGenerator();

    public static final int FRAME_UPDATE = 7;
    public static Font font;

    public static Main instance;

    BrickGenerator level1;
    BrickGenerator level2;
    BrickGenerator level3;
    BrickGenerator level4;

    Menu menu;
    Game game;

    Scene scene;

    private int clickedButton = 0;

    @Override
    public void run() {
        instance = this;
        addMouseListeners();
        font = new Font(Font.SERIF,Font.PLAIN,24);
        Game.GAME_X2 = getWidth();
        Game.GAME_Y2 = getHeight();

        Game.BRICK_WIDTH = (Game.GAME_X2 - Game.GAME_X1 - Game.BRICK_SEP * (Game.BRICK_IN_ROW - 1)) / Game.BRICK_IN_ROW;

        level1 = new Level4();
        level2 = new Level1();
        level3 = new Level2();
        level4 = new Level3();

        startMenu();


        waitForGame();
    }

    private void waitForGame() {
        while(true){
            if(clickedButton != 0){
                boolean con = startGame(clickedButton);
                if(!con)break;
                else {
                    clickedButton++;
                    if(clickedButton > 4)clickedButton = 1;
                }
            }
            pause(150);
        }
    }

    public void startMenu(){
        scene = Scene.MENU;
        menu = new Menu(getGCanvas());
        menu.run();

    }

    public boolean startGame(int level){
        game = new Game(getGCanvas());
        scene = Scene.GAME;
        game.setLevel(getLevel(level));
        return game.run();
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        if(scene == Scene.GAME)game.mouseMoved(mouseEvent);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if(scene == Scene.MENU){
            int x = menu.mouseClicked(mouseEvent);
            if(x != 0){
                clickedButton = x;
            }
        }
    }

    private BrickGenerator getLevel(int x){
        if(x == 1)return level1;
        if(x == 2)return level2;
        if(x == 3)return level3;
        if(x == 4)return level4;
        return null;
    }
}