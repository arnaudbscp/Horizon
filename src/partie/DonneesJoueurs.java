package partie;

import java.util.ArrayList;
import description.Description;
import description.Tacheclass;
import strategie.Strategie;

public class DonneesJoueurs implements DonneesJoueur {
	private int montantCaisse; 
	private int qualiteProduit; 
	private ArrayList<Realisation> liste;
	private String nom;
	private Strategie s;
	
	public DonneesJoueurs(String nom) {
		this.nom = nom;
		this.montantCaisse = 300; 
		this.qualiteProduit = 0;
		liste = new ArrayList<>();
		Description d = new Description();
		for (int i = 0; i < d.getTaches().size(); i++) {
			liste.add(new Realisation((Tacheclass) d.getTaches().get(i)));
		}
		liste.get(0).setEtat(Etat.EN_COURS);
	}
	
	public void actualisation(int temps) {}
	
	public void baisseQualite(int delta) {
		this.qualiteProduit -= delta;
	}
	
	public void depense(int somme) {
		this.montantCaisse -= somme;
	}
	
	public int getCaisse() {
		return this.montantCaisse;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public int getQualite() {
		return this.qualiteProduit;
	}
	
	public Realisation getRealisation(String id) {
		for (Realisation r : liste) {
			if (r.getTache().getId().equals(id)) {
				return r;
			}
		}
		return null;
	}

	@Override
	public ArrayList<Realisation> getRealisation() {
		return liste;
	}

	@Override
	public Strategie getStrategie() {
		return s;
	}
	
}
