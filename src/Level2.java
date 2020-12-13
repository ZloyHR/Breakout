import acm.graphics.GCanvas;

public class Level2 extends BrickGenerator{
    @Override
    /**Level2 generator*/
    public int generate(GCanvas canvas) {
        super.generate(canvas);
        int cnt = 1;
        for(int i = 0;i <= 7; ++i){
            double rawWidth = (width + Game.BRICK_SEP) * (cnt - 1) + width;
            x = (Game.GAME_X1 + Game.GAME_X2) / 2 - rawWidth / 2;
            for(int j = 0; j < cnt; ++j){
                Brick brick = new Brick("img/WoodenBrick" + (i / 2 + 1) + ".png");
                brick.setLocation(x,y);
                brick.scale(width/150,height/30);
                canvas.add(brick);
                brickCount++;
                x += width + Game.BRICK_SEP;
            }
            y += height + Game.BRICK_SEP;
            cnt += 2;
        }
        return brickCount;
    }
}
