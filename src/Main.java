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

    /**Random generator*/
    public static RandomGenerator gen = new RandomGenerator();

    /**How fast game redraw freames*/
    public static final int FRAME_UPDATE = 7;
    /**Game font*/
    public static Font font;

    /**Game instance*/
    public static Main instance;

    /**Game levels*/
    BrickGenerator level1;
    BrickGenerator level2;
    BrickGenerator level3;
    BrickGenerator level4;

    /**Game scenes*/
    Menu menu;
    Game game;
    EndGame endGame;

    /**Current scene*/
    Scene scene;

    /**Is level button clicked*/
    private int clickedButton = 0;
    /**Menu or Exit button clicked*/
    private int toContinue = 0;

    @Override
    /**Main cycle*/
    public void run() {
        instance = this;
        SoundClip soundtrack = new SoundClip("fx/soundTrack (1).wav");
        soundtrack.setVolume(0.2);
        soundtrack.loop();
        soundtrack.play();
        addMouseListeners();
        //font = new Font(Font.SERIF,Font.PLAIN,24);
        try {
            font = Font.createFont(Font.TRUETYPE_FONT,new File("fonts/RobotoMono-VariableFont_wght.ttf"));
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Game.GAME_X2 = getWidth();
        Game.GAME_Y2 = getHeight();

        Game.BRICK_WIDTH = (Game.GAME_X2 - Game.GAME_X1 - Game.BRICK_SEP * (Game.BRICK_IN_ROW - 1)) / Game.BRICK_IN_ROW;

        level1 = new Level4();
        level2 = new Level1();
        level3 = new Level2();
        level4 = new Level3();

        startMenu();

    }

    /**Figure what to do if game ends or scene switches back to menu*/
    private void waitForGame() {
        while(true){
            if(clickedButton != 0){
                boolean con = startGame(clickedButton);
                if(!con) {
                    SoundClip lose = new SoundClip("fx/lose.wav");
                    lose.setVolume(0.4);
                    lose.play();
                    pause(lose.getDuration() * 1000);
                    if(endGame()){
                        System.exit(0);
                    }else{
                        game.resetGame();
                        startMenu();
                    }
                    break;
                }
                else {
                    clickedButton++;
                    if(clickedButton > 4)clickedButton = 1;
                }
            }
            pause(150);
        }
    }

    /**Creates Menu*/
    public void startMenu(){
        clickedButton = toContinue = 0;
        scene = Scene.MENU;
        menu = new Menu(getGCanvas());
        menu.run();
        waitForGame();
    }

    /**
     * Creates Game
     * @param level Number of game level
     * @return Is level completed
     */
    public boolean startGame(int level){
        game = new Game(getGCanvas());
        scene = Scene.GAME;
        game.setLevel(getLevel(level));
        return game.run();
    }

    /**
     * Creates EndGame
     * @return To exit game
     */
    public boolean endGame(){
        endGame = new EndGame(getGCanvas());
        scene = Scene.END_GAME;
        endGame.run();
        while(true){
            if(toContinue == 1)return false;
            if(toContinue == 2)return true;
            pause(150);
        }
    }

    @Override
    /**Figure cursor movement*/
    public void mouseMoved(MouseEvent mouseEvent) {
        if(scene == Scene.GAME)game.mouseMoved(mouseEvent);
    }

    @Override
    /**Figure mouse click*/
    public void mouseReleased(MouseEvent mouseEvent) {
        if(scene == Scene.MENU){
            int x = menu.mouseClicked(mouseEvent);
            if(x != 0){
                clickedButton = x;
            }
        }
        if(scene == Scene.END_GAME){
            int x = endGame.mouseClicked(mouseEvent);
            if(x != 0){
                toContinue = x;
            }
        }
    }

    /**Returns level by number*/
    private BrickGenerator getLevel(int x){
        if(x == 1)return level1;
        if(x == 2)return level2;
        if(x == 3)return level3;
        if(x == 4)return level4;
        return null;
    }
}