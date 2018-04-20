package partie;

import java.util.ArrayList;

import description.Couleur;
import description.Description;

public class Partie {
	
	private Description description; 
	private String[] joueurs; 
	private ArrayList<VueJoueurs> liste;
	
	public Partie(Description d, String[] tab) {
		liste = new ArrayList<>();
		this.description = d; 
		this.joueurs = tab;
		for(int i = 0; i< tab.length; i++) {
			liste.add(new VueJoueurs(tab[i]));
		}
	}
	
	public DonneesJoueurs getVueJoueur(String joueur) {
		for(VueJoueurs j : liste) {
			if(j.getNom() == joueur) {
				return j.getJoueur(); 
			}
		}
		return null;
	}
	
	public void passerTour() {
		
	}
	
	public void tourSemaine(Couleur c) {
	
	}
	
}
