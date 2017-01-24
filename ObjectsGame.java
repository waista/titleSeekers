
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;


public class ObjectsGame extends BasicGame {

	private GameContainer container;
	private Map map = new Map();
	private Player player = new Player(map);
	
	private TriggerController triggers = new TriggerController(map, player);
	private Camera camera = new Camera(player);
	private Hud hud = new Hud();



	public ObjectsGame() {
		super("title seekers");
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		this.container = container;
		//Music background = new Music("res/ethfj");
		//background.loop();
		this.map.init();
		this.player.init();
		PlayerController controler = new PlayerController(this.player);
		container.getInput().addKeyListener(controler);
		this.hud.init();
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		this.camera.place(container, g);
		this.map.renderBackground();
		this.player.render(g);
		this.map.renderForeground();
		this.hud.render(g);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		String message[] = this.triggers.update();
		if(message != null){
			this.hud.update(message);
		}
		this.player.update(delta);
		this.camera.update(container);
		
	}

	@Override
	public void keyReleased(int key, char c) {
		if (Input.KEY_ESCAPE == key) {
			this.container.exit();
		}
	}

	@Override
	public void keyPressed(int key, char c) {
	}
	
	public Hud getHud(){
		return hud;
	}
}
