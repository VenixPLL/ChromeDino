package pl.venixpll.app.render;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import pl.venixpll.app.ChromeDinoAPP;

public interface IRender {

    void tick(GameContainer container,ChromeDinoAPP app);

    void draw(Graphics graphics, GameContainer container, ChromeDinoAPP app);
}
