import acm.graphics.GCanvas;

public class Level4 extends BrickGenerator{
    @Override
    /**Level4 generator*/
    public int generate(GCanvas canvas) {
        super.generate(canvas);
        for(int i = 0;i <= 7; ++i){
            x = Game.GAME_X1 - width - Game.BRICK_SEP;
            for(int j = 0;j <= Game.BRICK_IN_ROW; ++j){
                x += width + Game.BRICK_SEP;
                Brick brick = new Brick("img/WoodenBrick" + (i / 2 + 1) + ".png");
                brick.setLocation(x,y);
                brick.scale(width/150,height/30);
                canvas.add(brick);
                brickCount++;
            }
            y += height + Game.BRICK_SEP;
        }
        return brickCount;
    }
}
