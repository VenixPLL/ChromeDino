package pl.venixpll;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import pl.venixpll.app.ChromeDinoAPP;

public class ChromeDino {

    public static void main(String[] args) throws SlickException {
        final AppGameContainer container = new AppGameContainer(new ChromeDinoAPP("ChromeDino"));
        container.setVSync(true);
        container.setTargetFrameRate(60);
        container.setDisplayMode(800,600,false);
        container.start();
    }

}
