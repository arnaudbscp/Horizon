package partie;

import java.util.ArrayList;

import strategie.Strategie;

public class DonneesJoueurs implements DonneesJoueur {
	private int montantCaisse; 
	private int qualiteProduit; 
	private ArrayList<Realisation> liste;
	private String nom;
	
	public DonneesJoueurs(String nom) {
		this.nom = nom;
		this.montantCaisse = 300; 
		this.qualiteProduit = 100;
	}
	
	public void actualisation(int temps) {
		
	}
	
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
		
	}

	@Override
	public Realisation getRealisation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Strategie getStrategie() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
