package description;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 * @author bascopa
 *<p> Class qui implemente Tache. Voir l'interface pour plus de précisions.</p>
 */

public class Tacheclass implements Tache {
	private int coutAcceleration;
	private Aleas[] aleas;
	private String description;
	private int dureeInitiale;
	private int dureeMax;
	private int avancement;
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
		this.predecesseurs = new ArrayList<Tache>();
		this.successeurs = new ArrayList<Tache>();
		
	}
	
	public Aleas[] getAleas() {
		return this.aleas;
	}
	
	public void avancer() {
		avancement++;
	}
	
	public void reculer() {
		avancement--;
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
		if(this.predecesseurs.isEmpty()) {
		Description d= new Description();
		HashMap<String, String[]> relations = d.getRelation();
		for(HashMap.Entry<String,String[]> e : relations.entrySet()) {
			String[] rel = e.getValue();
			for(String a : rel) {
				if(a == this.id) {
					this.predecesseurs.add(d.getTacheById(e.getKey()));
				}
			}
		}
		}
		return this.predecesseurs;
	}
	public final Collection<Tache> getSuccesseurs() {
		if(this.successeurs.isEmpty()) {
			Description d = new Description();
			String[] a = d.getRelation().get(this.id);
			for(String b : a) {
				this.successeurs.add(d.getTacheById(b));
			}
		}
		return this.successeurs;
		
	}
	
	public int getAvancement() {
		return avancement;
	}

	@Override
	public void setDuree(int i) {
		this.dureeInitiale = i;
	}
}
