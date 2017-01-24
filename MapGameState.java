
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class MapGameState extends BasicGameState {
	public static final int ID = 2;

	private GameContainer container;
	private Map map = new Map();
	private Player player = new Player(map);
	
	private TriggerController triggers = new TriggerController(map, player);
	private Camera camera = new Camera(player);
	private Hud hud = new Hud();
	private StateBasedGame game;
//	private boolean vaJouerAuBlackJack;
	private Image imageFin;
	
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.container = container;
		int numMusique = 2;
		
		int alea = 1 + (int)(Math.random() * ((1000 - 1) + 1));
		Music background;
		
		if(alea == 300){
			background = new Music("res/music/musique.ogg");
		}else{
			background = new Music("res/music/"+ numMusique+".wav");
		}
		background.loop();
		this.map.init();
		this.player.init();
		PlayerController controler = new PlayerController(this.player);
		container.getInput().addKeyListener(controler);
		this.hud.init();
//		vaJouerAuBlackJack = false;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		this.camera.place(container, g);
		this.map.renderBackground();
		this.player.render(g);
		this.map.renderForeground();
		this.hud.render(g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		String message[] = this.triggers.update();
		if(message != null){
			this.hud.update(message);
		}
		this.player.update(delta);
		this.camera.update(container);
//		if(vaJouerAuBlackJack){
//			game.enterState(BlackJack.ID);
//		}
	}

	@Override
	public void keyReleased(int key, char c) {
		if (Input.KEY_ESCAPE == key) {
			this.container.exit();
		}
	}

	  /**
	   * L'identifiant permet d'identifier les différentes boucles.
	   * Pour passer de l'une à l'autre.
	   */
	  public int getID() {
	    return ID;
	  }
	  
//	  public void setVaJouerAuBlackJack(boolean boule){
//		  vaJouerAuBlackJack = boule;
//	  }
	  
	@Override
	public void keyPressed(int key, char c) {
	}
	
	public Hud getHud(){
		return hud;
	}
}
