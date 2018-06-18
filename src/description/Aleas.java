package description;

import partie.DonneesJoueur;
import partie.Realisation;

/**
 * 
 * @author bascopa
 * <p>Class qui implémente l'interface Alea.</p>
 * <p>Voir l'interface pour plus de détails.</p>
 */

public class Aleas implements Alea{
	
	private TypeAlea typeA;
	private int grav;
	private Couleur color;
	
	public Aleas(TypeAlea type, int grav, Couleur color) {
		this.typeA=type;
		this.grav=grav;
		this.color=color;
	}
	
	public void appliquer(DonneesJoueur equipe, Realisation r) {
		// a remplir
	}

	public int getGravite() {
		return this.grav;
	}
	public TypeAlea getType() {
		return this.typeA;
	}
	
}
