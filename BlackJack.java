
import org.lwjgl.input.Keyboard;
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

import java.util.*;


public class BlackJack extends BasicGameState {
	public static final int ID = 3;
	private StateBasedGame game;

	private ArrayList<Integer> deck;
	
//	private GameContainer container;
//	private Map map = new Map();
//	private Player player = new Player(map);
//	
//	private TriggerController triggers = new TriggerController(map, player);
//	private Camera camera = new Camera(player);
//	private Hud hud = new Hud();
	private Image background;
	
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
//		this.container = container;
//		//Music background = new Music("res/ethfj");
//		//background.loop();
//		//this.map.init();
//		//this.player.init();
//		//PlayerController controler = new PlayerController(this.player);
//		container.getInput().addKeyListener(controler);
	    this.background = new Image("res/blackjack.png");
	    this.game = game;

		
	    
		
		deck = new ArrayList<Integer>();
		int j;
		for (int i = 1; i < 14 ; i++ ) { // on rajoute les 13 numeros de cartes
			for (j = 0; j < 4 ; j ++ ) { // il y a quatre cartes de chaque numero
				deck.add(i);//on rajoute au deck
			}
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		//this.camera.place(container, g);
		//this.map.renderBackground();
		//this.player.render(g);
		//this.map.renderForeground();
		//this.hud.render(g);
		background.draw(0, 0, container.getWidth(), container.getHeight());
	    

	}


	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
//		String message[] = this.triggers.update();
//		if(message != null){
//			this.hud.update(message);
//		}
//		this.player.update(delta);
//		this.camera.update(container);
		Input input = container.getInput();
	    if(input.isKeyPressed(Keyboard.KEY_ESCAPE))
	    {
	        input.clearKeyPressedRecord();
	        game.enterState(2);
	    }
		
	}

	  /**
	   * L'identifiant permet d'identifier les différentes boucles.
	   * Pour passer de l'une à l'autre.
	   */
	  public int getID() {
	    return ID;
	  }
	  
	  public void test () {
		  game.enterState(BlackJack.ID);
	  }
	  
	  @Override
	  public void keyPressed(int key, char c) { 
	    game.enterState(MapGameState.ID);
	  }
	
	
//	public Hud getHud(){
//		return hud;
//	}

		/*
		---------------------------------------------------------------------------------------------------------------------------
														Fonctions utilitaires
		---------------------------------------------------------------------------------------------------------------------------
		*/

	public int random(int min,int max){
		//fonction qui retourne une valeur pseudo Aleatoire entre la valeur min et la valeur max incluse
		return min + (int)(Math.random() * ((max - min) + 1));
	}

	public int tireUneCarte(){
		//fonction qui tire une carte aleatoire du deck de cartes
		if (deck.size() < 1) { // si le deck est vide on annonce un probleme
			return -1;
		}

		int numeroAleatoire = random(0,deck.size());//on prend un indice au hasard
		int maCarte = deck.get(numeroAleatoire);//on tire cet indice dans le deck
		deck.remove(numeroAleatoire);// on supprime la carte qu'on a retiré

		//attribution de la bonne valeur
		if (maCarte >10) {
			maCarte = 10;
		}

		return maCarte;//et on retourne la bonne carte

	}


	/*
	---------------------------------------------------------------------------------------------------------------------------
													Fonctions generales du systeme de jeu
	---------------------------------------------------------------------------------------------------------------------------
	*/

	public boolean recupTest(){
		//fonction de test qui recuperes les donnes entrÃ©es par le joueur
		while(true){
			System.out.println("Vous pouvez appuyer sur q pour sortir");
			System.out.println("appuyez sur y pour dire oui");
			System.out.println("appuyez sur n pour dire non");
			String input = System.console().readLine();
			if ("q".equals(input)) {
				System.out.println("Exit!");
				System.exit(0);
			}
			if ("y".equals(input)) {
				return true;
			}
			if ("n".equals(input)) {
				return false;
			}
		}
	}

	public int tourIA(){
		int valeurIA = tireUneCarte();
		valeurIA += tireUneCarte();
		while(valeurIA <13 || valeurIA> 21){
			valeurIA += tireUneCarte();
		}
		return valeurIA;
	}

	public void jeu(){
		//fonction qui geres le jeu en son entieretÃ©e
		System.out.println("vous jouez au Blackjack");

		int valeurJoueur = 0;
		int valeurIA = 0;
		int valeurTmp = 0;

		//distribution des deux premieres cartes
		valeurTmp = tireUneCarte();
		System.out.println("vous tirez un " + valeurTmp);
		valeurJoueur += valeurTmp;
			
		boolean verif = true;// on fin du jeu = false
		while(verif){//tant que c'est vrai on continue
			valeurTmp = tireUneCarte();
			System.out.println("vous tirez un " + valeurTmp);
			valeurJoueur += valeurTmp;
			System.out.println("vous avez maintenant " + valeurJoueur);
			System.out.println("continuez vous ?");
			verif = recupTest();
		}
		if (valeurJoueur > 21) {
			System.out.println("vous avez depasse 21, vous avez perdu");
		}else{
			valeurIA = tourIA();// une fois que le joueur a fini, au tour de l'ordi 
			if (valeurJoueur == 21) {
				System.out.println("Incroyable ! un 21 ! vous avez gagné !");
			}
			if (valeurIA > 21) {
				System.out.println("votre adversaire a perdu, bravo, vous avez eu de la chance.");
			}
			System.out.println("votre adversaire a eu " + valeurIA);
			if (valeurJoueur > valeurIA){
				System.out.println("vous avez gagné");
			}else{
				System.out.println("vous avez perdu");
			}
		}
	}	
}
