package partie;

import java.util.ArrayList;

import description.Description;

public class Partie {
	
	private Description description; 
	private String[] joueurs; 
	private ArrayList<DonneesJoueurs> liste;
	
	public Partie(Description d, String[] tab) {
		this.description = d; 
		this.joueurs = tab;
		for(String s : tab) {
			liste.add(new DonneesJoueurs(s));
		}
	}
	
	public VueJoueurs getVueJoueur(String joueur) {
		for(DonneesJoueurs j : liste) {
			if(j.getNom() == joueur) {
				return new VueJoueurs(j); 
			}
		}
		return null;
	}
}
