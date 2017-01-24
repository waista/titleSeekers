
import java.io.IOException;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {
    public static void main(String[] args) throws IOException, SlickException{ 
    	AppGameContainer app = new AppGameContainer(new StateGame());
	    app.setDisplayMode(800, 600, false);
	    app.setForceExit(true);
	    app.setTargetFrameRate(1000);
	    app.start();	 
	    
    }
}
