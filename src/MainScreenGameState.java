
import java.awt.Color;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainScreenGameState extends BasicGameState {

	  public static final int ID = 1;
	  private Image background;
	  private StateBasedGame game;
	  private int imageDeLaVideo;
	  private String message;

	  @Override
	  public void init(GameContainer container, StateBasedGame game) throws SlickException {
	    this.game = game;
	    imageDeLaVideo = 1;
	    message = "";
	    this.background = new Image("res/menu.png");
	  }

	  /**
	   * Contenons nous d'afficher l'image de fond. 
	   * Le text est placé approximativement au centre.
	   */
	  @Override
	  public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
	    background.draw(0, 0, container.getWidth(), container.getHeight());
	  }

	  /**
	  * Passer à l’écran de jeu à l'appui de n'importe quel touche.
	  */
	  @Override
	  public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if(imageDeLaVideo <10){
			String mesZeros = "";
			if(imageDeLaVideo<100){
					mesZeros = "0";
			}
			if(imageDeLaVideo< 10){
				  mesZeros = "00";
			 } else  
			this.background = new Image("res/video/projet jeu_000"+mesZeros+""+ imageDeLaVideo+".png");
			imageDeLaVideo++;
			
		}else{
			this.background = new Image("res/menu.png");
			message = "Appuyez sur une touche";
		}
		  
	  }

	  @Override
	  public void keyReleased(int key, char c) {
		    game.enterState(MapGameState.ID);
	  }

	  /**
	   * L'identifiant permet d'identifier les différentes boucles.
	   * Pour passer de l'une à l'autre.
	   */
	  @Override
	  public int getID() {
	    return ID;
	  }
	}
