package partie;

import description.Tacheclass;

public class Realisation {

	private boolean[] protec;
	private boolean acceleration=false;
	private Tacheclass tache;
	private Etat etat;
	
	public Realisation(Tacheclass tache) {
		this.tache = tache;
		this.etat = Etat.NON_ENTAMEE;
	}
	
	public void setProtection() {
		
		
	}

	public Tacheclass getTache() {
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
}
