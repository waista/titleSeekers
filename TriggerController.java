
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class TriggerController {

	private Map map;
	private Player player;
	//boolean pnj = false;
	private Hud hud = new Hud();
	//private MapGameState maPartie = new MapGameState();
	private int etatPenduPoupet;
	private int viesRestantes;
	
	
	
	//pour pendu
	private LettreDePendu [] mesLettres;
	private int taille;
	private int lettresTrouvees;

	public TriggerController(Map map, Player player) {
		viesRestantes = 8;
		this.map = map;
		this.player = player;
		
		//Pour pendu
		String tmp = choixMot(random(1,13));// on choisit un mot aleatoire
		taille = tmp.length();
		lettresTrouvees = 0;
		etatPenduPoupet = 0;

		char [] mesCharacteres = tmp.toCharArray();
		mesLettres = new LettreDePendu[taille];
		for (int i = 0;i<taille ;i++ ) {
			mesLettres[i] = new LettreDePendu(mesCharacteres[i]);
		}
	}

	public String[] update() throws SlickException {
		this.player.setOnStair(false);
		for (int objectID = 0; objectID < this.map.getObjectCount(); objectID++) {
			if (isInTrigger(objectID)) {
				if ("teleport".equals(this.map.getObjectType(objectID))) {
					this.teleport(objectID);
				} else if ("stair".equals(this.map.getObjectType(objectID))) {
					this.player.setOnStair(true);
				} else if ("change-map".equals(this.map.getObjectType(objectID))) {
					this.changeMap(objectID);
				} else  if ("chollet".equals(this.map.getObjectType(objectID))) {
					//pnj = true;
					String[] message = new String[4];
					message[0] = "Chaullet : Va chercher un titre";
					message[1] = "Sinon, bats toi !";
					message[2] = "Je te défies en dddddduel";
					message[3] = "au pendu.";
					return message;
				} else  if ("poupet".equals(this.map.getObjectType(objectID))) {
					String[] message = new String[4];
					if(etatPenduPoupet == 0){//debut du jeu
						message[0] = "Poupée:";
						message[1] = "*Vroum*, jouons au blackjack ";
						message[2] = "tu penses vraiment avoir le choix ? ";
						message[3] = "pas eu le temps de coder cette fonctionnalité";
						//etatPenduPoupet++;
					}
//						else if(etatPenduPoupet == 1){//duree du jeu
//						message = jeu();
//						String essaiDuJoueur = recupDonne();
//						if(finPartie(essaiDuJoueur)){
//							etatPenduPoupet++;
//						}
//					}else if(etatPenduPoupet == 2){//joueur a gagner, ne peut pas rejouer
//						message[0] = "Poupet:";
//						message[1] = "*Vroum*, Bravo !";
//						message[2] = "tu m'as battu ! ";
//						message[3] = "un indice pour ton titre. Recommence le jeu.";
//					}else if(etatPenduPoupet == -1){//joueur a perdu
//						message[0] = "Poupet:";
//						message[1] = "*Vroum*, tu es mauvais ";
//						message[2] = "essaye encore. ";
//						message[3] = "Tu n'auras jamais de titre!";
//						etatPenduPoupet = 0;//il peut rejouer
//					}
					return message;
				}
				else  if ("breton".equals(this.map.getObjectType(objectID))) {
					//pnj = true;
					String[] message = new String[4];
					message[0] = "elbreton : Désolé, la salle 133 est occupée !";
					message[1] = "Vroum. Les developpeurs aimaient bien les voitures";
					message[2] = "PIPE. Tu connais les CSS ? ...";
					message[3] = "C'est bien.";
					return message;
				}else  if ("coffre".equals(this.map.getObjectType(objectID))) {
					//pnj = true;

					String[] message = new String[4];
					message[0] = "Coffre : Je suis un coffre.";
					message[1] = "*Vroum*, J'aimerais bien etre une voiture";
					message[2] = "d'ailleurs au cas ou tu cherchais, le titre du jeu ";
					message[3] = "c'est title seekers. Au Revoir. Tu as fini le jeu :)";
					
					//maPartie.setVaJouerAuBlackJack(true);
					try{
						finDuJeu();
					}catch(InterruptedException e){
						//oui
					}

					
					return message;
				}
				
			}
			
		}
		return null;
	}

	public void finDuJeu() throws InterruptedException {
		
		Thread.sleep(3000);
		
		System.exit(0);
	}

	private boolean isInTrigger(int id) {
		return this.player.getX() > this.map.getObjectX(id)
				&& this.player.getX() < this.map.getObjectX(id) + this.map.getObjectWidth(id)
				&& this.player.getY() > this.map.getObjectY(id)
				&& this.player.getY() < this.map.getObjectY(id) + this.map.getObjectHeight(id);
	}

	private void teleport(int objectID) {
		this.player.setX(Float.parseFloat(this.map.getObjectProperty(objectID, "dest-x",
				Float.toString(this.player.getX()))));
		this.player.setY(Float.parseFloat(this.map.getObjectProperty(objectID, "dest-y",
				Float.toString(this.player.getY()))));
	}

	private void changeMap(int objectID) throws SlickException {
		this.teleport(objectID);
		String newMap = this.map.getObjectProperty(objectID, "dest-map", "undefined");
		if (!"undefined".equals(newMap)) {
			this.map.changeMap("res/map/" + newMap);
		}
	}
	
	
	
	
	
	



	/*
	---------------------------------------------------------------------------------------------------------------------------
													Fonctions utilitaires
	---------------------------------------------------------------------------------------------------------------------------
	*/

	public int random(int min,int max){
		//fonction qui retourne une valeur pseudo Aleatoire entre la valeur min et la valeur max incluse
		return min + (int)(Math.random() * ((max - min) + 1));
	}

	public String choixMot(int mot){
		String stringMotArenvoyer;
		//renvoie un des mots mis en mémoire a l'index indiqué
		 switch (mot) {
            case 1:  stringMotArenvoyer = "mot";
                     break;
            case 2:  stringMotArenvoyer = "lettre";
                     break;
            case 3:  stringMotArenvoyer = "enveloppe";
                     break;
            case 4:  stringMotArenvoyer = "feu";
                     break;
            case 5:  stringMotArenvoyer = "xylophone";
                     break;
            case 6:  stringMotArenvoyer = "code";
                     break;
            case 7:  stringMotArenvoyer = "jeu";
                     break;
            case 8:  stringMotArenvoyer = "moutarde";
                     break;
            case 9:  stringMotArenvoyer = "tulipe";
                     break;
            case 10: stringMotArenvoyer = "licorne";
                     break;
            case 11:stringMotArenvoyer = "confiture";
                     break;
            case 12: stringMotArenvoyer = "dernier";
                     break;
            default: stringMotArenvoyer = "impossible";
                     break;
        }
        return stringMotArenvoyer;
	}


//	public void afficherPendu(){
//		for (int i = 0 ;i <taille ; i++ ) {
//			if (mesLettres[i].estTrouve()) {//s'il est trouvé on l'affiche
//				System.out.print(mesLettres[i].getLettre());
//			}else{
//				System.out.print("_ ");
//			}
//		}
//		System.out.println(" ");// a la ligne a la fin
//	}

//	public void testMot(){
//		for (int i = 0 ;i <taille ; i++ ) {
//		
//				System.out.print(mesLettres[i].getLettre());
//		}
//		System.out.println(" ");// a la ligne a la fin
//	}

	public boolean testerMot(String motATester){
		//fonction qui verifie si le motATester est bien le mot du pendu renvoyant false si c'est le cas et true sinon
		if (motATester.length() != taille) {// on commence par verifier la taille des deux mots
			return false;
		}
		char [] mesCharacteres = motATester.toCharArray();
		for (int i = 0;i<taille ;i++ ) {
			if(mesLettres[i].getLettre() != mesCharacteres[i]){
				return true;
			}
		}
		return false;
	}

	public boolean testerCharactere(char charactereATester){
		//fonction qui verifie si le charactere a tester appartient au mot a deviner 
		//renvoie false si c'est le cas et change son état de trouvé a true
		//renvoie true sinon

		boolean verif = true;
		for (int i = 0;i<taille ;i++ ) {
			if(mesLettres[i].getLettre() == charactereATester && !mesLettres[i].estTrouve()){// le charactere peut etre trouvé plusieurs fois
				mesLettres[i].setTrouve(true);
				verif = false;// un charactere peut etre trouvé plusieurs fois dans un meme mot
			}
		}
		return verif;
	}


	/*
	---------------------------------------------------------------------------------------------------------------------------
													Fonctions generales du systeme de jeu
	---------------------------------------------------------------------------------------------------------------------------
	*/

	public String recupDonne(){
		//fonction de test qui recuperes les donnes entrées par le joueur
		// je n'ai pas une vraie entrée clavier pour n'importe quelle touche et ca me gonfle
		while(true){
			System.out.println("Vous pouvez appuyer sur q pour sortir");
			System.out.println("rentrez un charactere a tester ou un mot que vous pensez avoir deviner");
			System.out.println("ceci est une version de test");
			String input = System.console().readLine();

			if ("q".equals(input)) {
				System.out.println("Exit!");
				System.exit(0);
			}else{
				return input;
			}
		}
	}
	
	public boolean finDePartie(String essaiDuJoueur){
		boolean verif = true;
		if(viesRestantes <= 0){
			return true;
		}
		while(verif && viesRestantes > 0){
			if (essaiDuJoueur.length() > 1) {//s'il ecrit plus qu'un charactere, il teste un mot
				verif = testerMot(essaiDuJoueur);
			}else {//sinon on teste le charactere
				if(testerCharactere(essaiDuJoueur.charAt(0))){// s'il se trompe
					//System.out.println("et non !");
					return false;
				}else{
					//System.out.println("bravo !");
					lettresTrouvees++;
				}
				if (lettresTrouvees == taille) {//toutes les lettres sont trouvées
					//System.out.println("toutes les lettres sont trouvées");
					verif = false;
				}else{
					//System.out.println("Il vous reste "+ (8-cpt) + " vies");
				}
			}
		}
		return !verif;
	}


//	public String[] jeu(){
//		//fonction qui geres le jeu en son entieretée
//		System.out.println("vous jouez au Pendu");
//		int cpt = 0;
//		boolean verif = true;
//		String essaiDuJoueur;
//		while(verif && cpt <8){
//			afficherPendu();
//			testMot();// affiche le mot caché a supprimer dans la version finale
//			essaiDuJoueur = recupDonne();
//			if (essaiDuJoueur.length() > 1) {//s'il ecrit plus qu'un charactere, il teste un mot
//				verif = testerMot(essaiDuJoueur);
//			}else {//sinon on teste le charactere
//				if(testerCharactere(essaiDuJoueur.charAt(0))){// s'il se trompe
//					//System.out.println("et non !");
//					cpt++;
//				}else{
//					//System.out.println("bravo !");
//					lettresTrouvees++;
//				}
//				if (lettresTrouvees == taille) {//toutes les lettres sont trouvées
//					//System.out.println("toutes les lettres sont trouvées");
//					verif = false;
//				}else{
//					//System.out.println("Il vous reste "+ (8-cpt) + " vies");
//				}
//			}
//		}
//		if (verif) {
//			//System.out.println("vous avez perdu !");
//		}else{
//			//System.out.println("vous avez gagne !");
//		}
//
//
//	}	


}
