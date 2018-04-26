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
	}
	
	@Override
	public String toString() {
		return "Realisation [protec=" + Arrays.toString(protec) + ", acceleration=" + acceleration + ", tache=" + tache
				+ ", etat=" + etat + "]";
	}

	public void setProtection(Couleur c, boolean ok) {
		if(ok) {
			if(c.equals(Couleur.ROUGE)) {
				protec[0] = true;
			}else if(c.equals(Couleur.JAUNE)) {
				protec[1] = true;
			}else {
				protec[2] = true;
			}
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
		if(acceleration) {
			this.acceleration = acceleration;
		}
	}
}
