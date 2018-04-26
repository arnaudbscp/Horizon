package description;
import java.util.Random;
public enum Couleur {
	ROUGE,
	JAUNE,
	VERT;
	
	public static Couleur tirage() {
		Random rand = new Random();
		int i=rand.nextInt(6)+1;
		if(i<4) {
			return Couleur.ROUGE;
		} else if(i<6) {
			return Couleur.JAUNE;
		} else {
			return Couleur.VERT;
		}
	}
	
}
