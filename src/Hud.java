
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.*;

public class Hud {

	private Image playerbars;
	private static final int P_BAR_X = 100;
	private static final int P_BAR_Y = 500;
	private String[] message;
	public boolean test;

	public void init() throws SlickException {
		this.playerbars = new Image("res/hud.png");
		message = new String[4];
		for(int i = 0; i <4; i++){
			message[i] = "";
		}
		test = false;
	}
	
	public void render(Graphics g) {
		g.resetTransform();
		g.drawImage(this.playerbars, P_BAR_X, P_BAR_Y);
		dialogue(g,message);
	}
	
	
	public void dialogue(Graphics g, String [] str){
		g.drawImage(this.playerbars, P_BAR_X, P_BAR_Y);
		for(int i = 0; i <4; i++){
			g.drawString(str[i], 110, 515 + 15*i);
		}
	}
	
//	public void dialogue2(Graphics g, String [] str){
//		g.drawString(str[0], 110, 515 + 15*i);
//		g.drawString(str[1], 110, 515 + 15*i);
//		g.drawString(str[2], 110, 515 + 15*i);
//		g.drawString(str[3], 110, 515 + 15*i);
//	}
	
	
	
//	public int dialogue (Graphics g, String str) {
//		//return -1 si le string a été affiché correctement
//		//sinon return l'index du derniere charactere affiché
//		g.drawImage(this.playerbars, P_BAR_X, P_BAR_Y);
//		int indexDuDernierEspace = 0;
//		int numeroDeLignes = 0;
//		int debutDerniereLigne = 0;
//		boolean verif = true;
//		int i;
//		if(str.length() > 60){
//			char mesCharacteres[] = str.toCharArray();
//			while(numeroDeLignes <3 && verif){
//				for(i = 0 + debutDerniereLigne; i<60+debutDerniereLigne; i++){
//					if(mesCharacteres.length > i-1){
//						return -1;
//					}else{
//						System.out.println(mesCharacteres[i]);
//						if(mesCharacteres[i] == ' '){
//							indexDuDernierEspace = i + debutDerniereLigne;
//						}
//					}
//					g.drawString(Arrays.toString(mesCharacteres), 110, 515 + 15*numeroDeLignes);
//				}
//				debutDerniereLigne = indexDuDernierEspace;
//				numeroDeLignes++;
//			}
//			if(verif){
//				return debutDerniereLigne;
//			}else{
//				return -1;
//			}
//			
//		}else{
//			g.drawString(str, 110, 515);
//			return -1;
//		}
//		
//	}
	
	public void update(String[] str) {
		message = str;
	}
}
