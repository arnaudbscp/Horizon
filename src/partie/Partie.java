package partie;

import java.util.ArrayList;

import description.Aleas;
import description.Couleur;
import description.Description;
import description.TypeAlea;

public class Partie {
	
	private Description description; 
	private int tour;
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
	
	public VueJoueur getVueJoueur(String joueur) {
		for(VueJoueurs j : liste) {
			if(j.getNom() == joueur) {
				return (VueJoueur) j.getJoueur(); 
			}
		}
		return null;
	}
	
	public void passerTour() {
		ArrayList<Realisation> l = getActu();
		for (Realisation r : l) {
			if (r.getTache().getAvancement() < r.getTache().getDureeInitiale())
				r.getTache().avancer();
		}
		tour++;
	}
	
	public ArrayList<Realisation> getActu() {
		ArrayList<Realisation> r = new ArrayList<>();
		for(int i = 0; i < joueurs.length;i++) {
			for (Realisation rea : liste.get(i).getJoueur().getRealisation()) {
				if (rea.getEtat().equals(Etat.EN_COURS))
					r.add(rea);
			}
		}
		return r;
	}
	
	public void tourSemaine(Couleur c) {
		ArrayList<Realisation> l = getActu();
	}
	
}
