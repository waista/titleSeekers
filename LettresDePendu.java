
import java.util.*;

class LettreDePendu{
	private char lettre;
	private boolean estTrouve;

	public LettreDePendu(char l){
		lettre = l;
		estTrouve = false;
	}



	public char getLettre(){
		return lettre;
	}

	public boolean estTrouve(){
		return estTrouve;
	}

	public void setTrouve(boolean tmp){
		estTrouve = tmp;
	}
}