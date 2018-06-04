package partie;

import java.util.Arrays;
import description.Couleur;
import description.Tache;
import description.Tacheclass;

public class Realisation {

	private boolean[] protec;
	private boolean acceleration;
	private Tacheclass tache;
	private Etat etat;
	
	public Realisation(Tacheclass t) {
		this.tache = t;
		this.etat = Etat.NON_ENTAMEE;
		this.acceleration = false;
		protec = new boolean[3];
	}
	
	@Override
	public String toString() {
		return "Realisation [protec=" + Arrays.toString(protec) + ", acceleration=" + acceleration + ", tache=" + tache
				+ ", etat=" + etat + "]";
	}

	public void setProtection(Couleur c, boolean ok) {
			if(c.equals(Couleur.ROUGE)) {
				protec[0] = ok;
			}else if(c.equals(Couleur.JAUNE)) {
				protec[1] = ok;
			}else {
				protec[2] = ok;
			}
	}

	public Tache getTache() {
		return tache;
	}
	
	public void setTache(Tacheclass tache) {
		this.tache = tache;
	}

	public Etat getEtat() {
		return etat;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}

	public void setAcceleration(boolean acceleration) {
		
			this.acceleration = acceleration;
		
	}

	public int getDuree() {
		return this.tache.getDureeInitiale();
	}
	public void avancer() { // Avancer de la Réalisation (pour mettre a jour les ETATS ou mettre un fonction avancer dans partie qui gere tout (cad les etats terminet les successeurs en etata iminents)
		if(this.tache.getDureeInitiale()>this.tache.getAvancement()) {
			this.tache.avancer();
		}
		if(this.tache.getDureeInitiale()==this.tache.getAvancement()) {
			etat=Etat.TERMINEE;
			
		}
	}
	public boolean isProtec(Couleur c) {
		if(c.equals(Couleur.ROUGE)) {
			return this.protec[0];
		}
		if(c.equals(Couleur.JAUNE)) {
			return this.protec[1];
		}else {
			return this.protec[2];
		}
	}
}
