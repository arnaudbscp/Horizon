package description;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


public class Tacheclass implements Tache {
	private int coutAcceleration;
	private Aleas[] aleas;
	private String description;
	private int dureeInitiale;
	private int dureeMax;
	private String id;
	private Collection<Tache> predecesseurs;
	private Collection<Tache> successeurs;
	
	public Tacheclass(int coutAcc, Aleas[] alea, String desc, int dureeInit, int dureeMax, String id) {
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
		if(couleur.equals(Couleur.ROUGE)) return aleas[0];
		else if(couleur.equals(Couleur.JAUNE)) {return aleas[1];			
		}
		return aleas[2];
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
		Description d= new Description();
		int index =  d.getDescription().indexOf(d.getTacheById(this.id));
		List<Tacheclass> ab = (List<Tacheclass>)d;
		ListIterator<Tacheclass> it =ab.listIterator(index);
		
		
	}
}
