package pl.venixpll.app.render;

import lombok.Data;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import pl.venixpll.app.ChromeDinoAPP;

import java.awt.*;

@Data
public class RenderCactus implements IRender {

    private int posX;
    private int posY;

    private Rectangle boundbox;

    public RenderCactus(int posX, int posY){

        this.posX = posX;
        this.posY = posY;
        this.boundbox = new Rectangle(posX,posY,40,60);
    }

    @Override
    public void tick(GameContainer container, ChromeDinoAPP app) {
        posX -= 6;
        if(posX < container.getWidth()) {
            int target = container.getHeight() - 210;
            if (posY != target || posY < target) {
                posY -= 4;
            }
            if (posY < target) {
                posY = target;
            }
        }
        this.boundbox.setBounds(posX,posY,40,60);
    }

    @Override
    public void draw(Graphics graphics, GameContainer container, ChromeDinoAPP app) {
        graphics.setColor(Color.green);
        graphics.fillRect(posX,posY,40,60);
        graphics.setColor(Color.white);
    }
}
