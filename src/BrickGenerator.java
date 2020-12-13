import acm.graphics.GCanvas;

public class BrickGenerator {
    /**Start coordinates and game field size*/
    public double width,height,x,y;
    /**Amount of generated bricks*/
    public int brickCount;

    /**Level generator*/
    public int generate(GCanvas canvas){
        width = Game.BRICK_WIDTH;
        height = Game.BRICK_HEIGHT;
        x = Game.GAME_X1;
        y = Game.GAME_Y1;
        brickCount = 0;
        return 0;
    }
}
