package pl.venixpll.app.render;

import lombok.Data;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import pl.venixpll.app.ChromeDinoAPP;

import java.awt.*;

@Data
public class RenderFloor implements IRender {

    private final int posX;
    private final int posY;
    private final int width;
    private final int height;

    private Rectangle boundbox;

    public RenderFloor(int posX, int posY, int width, int height){

        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.boundbox = new Rectangle(posX,posY,width,height);
    }

    @Override
    public void tick(GameContainer container, ChromeDinoAPP app) {

    }

    @Override
    public void draw(Graphics graphics, GameContainer container, ChromeDinoAPP app) {
        graphics.fillRect(posX,posY,width,height);
    }
}
