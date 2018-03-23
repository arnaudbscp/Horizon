package description;
import java.util.Collection;

public class Tacheclass implements Tache {
	private int coutAcceleration;
	private Alea[] aleas;
	private String description;
	private int dureeInitiale;
	private int dureeMax;
	private String id;
	private Collection<Tache> predecesseurs;
	private Collection<Tache> successeurs;
	
	public Tacheclass(int coutAcc, Alea[] alea, String desc, int dureeInit, int dureeMax, String id) {
		this.coutAcceleration = coutAcc; 
		this.aleas = alea;
		this.description = desc; 
		this.dureeInitiale = dureeInit;
		this.dureeMax = dureeMax; 
		this.id = id;
	}
	
	public int coutAcceleration() {
		return this.coutAcceleration;
	}
	public Alea getAlea(Couleur couleur) {
		return aleas[0]; //A COMPLETER
	}
	public String getDescription() {
		return this.description;
	}
	public int getDureeInitiale() {
		return this.dureeInitiale;
	}
	public int getDureeMax() {
		return this.dureeMax;
	}
	public String getId() {
		return this.id;
	}
	public Collection<Tache> getPredecesseurs() {
		return this.predecesseurs;
	}
	public Collection<Tache> getSuccesseurs() {
		return this.successeurs;
	}
}
