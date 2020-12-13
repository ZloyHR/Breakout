import acm.graphics.GCanvas;

public class Level1 extends BrickGenerator{
    @Override
    public int generate(GCanvas canvas) {
        for(int i = 0;i <= 7; ++i){
            x = Game.GAME_X1 - width - Game.BRICK_SEP;
            for(int j = 0;j <= Game.BRICK_IN_ROW; ++j){
                if(i != 0 || j != 0)continue;
                x += width + Game.BRICK_SEP;
                if(j % 4 == 3)continue;;
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
