
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PlusOuMoins extends BasicGameState {
	public static final int ID = 4;

	private GameContainer container;
	private Map map = new Map();
	private Player player = new Player(map);
	
	private TriggerController triggers = new TriggerController(map, player);
	private Camera camera = new Camera(player);
	private Hud hud = new Hud();
	private char tableChar[] = {'a','z','e','r','t','y','u','i','o','p','q','s','d','f','g','h','j','k','l','m','w','x','c','v','b','n'};

	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
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
	}
	
	public static int randomInt(int i){
		Random rand = new Random();
		i+=1;
		int j=rand.nextInt(i);
		return j;
	}

	//lance le jeux
	/*public boolean start(){
		int i = 1;
		int essai = 10;
		boolean find = false;
		int indice = randomInt(tableChar.length);
		char caracFind = tableChar[indice];
		int caracIntFind = (int)caracFind;
		Scanner sc = new Scanner(System.in);
		
		while(i<=essai && !find){
			System.out.println("Essai N° :"+i);
			String str = sc.nextLine();
			char caracIn = str.charAt(0);
			int caracIntIn = (int)(caracIn);
			if(caracIntFind==caracIntIn){
				System.out.println("Vous avez trouvé le bon caractere !!! ");
				find = true;
			}else if(caracIntFind>caracIntIn && i<essai){
				System.out.println("C'est + ... ");
			}else if(caracIntFind<caracIntIn && i<essai){
				System.out.println("C'est - ... ");
			}else{
				System.out.println("vous n'avez pas trouvé la bonne lettre sur les "+essai+" essais !!!");
			}
			i+=1;
		}
		return find;
	}
	*/

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
	  
	@Override
	public void keyPressed(int key, char c) {
	}
	
}
