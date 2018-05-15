package description;
import java.util.Random;
/**
 * <p>La liste des couleurs possibles. Une couleur caractérise un alea possible parmi tous les aleas envisagés à chaque étape. Dans le jeu, chaque couleur est liée à une probabilité d'apparition.</p>
 * 
 * @author bascopa
 *
 */
public enum Couleur {
	/**
	 * Couleur rouge : 3 chances sur 6.
	 */
	ROUGE,
	/**
	 * Couleur jaune (ou orange) : 2 chances sur 6.
	 */
	JAUNE,
	/**
	 * Couleur verte : 1 chance sur 6.
	 */
	VERT;
	
	/**
	 * Choisit aléatoirement une couleur avec les probabilités associées.
	 * @return La couleur choisie
	 */
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
