package partie;

import java.util.ArrayList;
import description.Couleur;
import description.Description;
import description.TypeAlea;

public class Partie {
	
	private Description description; 
	private int tour;
	private String joueur; 
	private ArrayList<VueJoueurs> liste;
		
	public Partie(Description d, String j) {
		liste = new ArrayList<>();
		this.description = d; 
		this.joueur = j;
		liste.add(new VueJoueurs(j));
	}
	
	public VueJoueurs getVueJoueur(String joueur) {
		for(VueJoueurs j : liste) {
			if(j.getNom().equalsIgnoreCase(joueur)) {
				return  j; 
			}
		}
		return null;
	}
	
	public void passerTour() {
		ArrayList<Realisation> l = getActu();
		
		for (Realisation r : l) {
				r.getTache().avancer();
				tour++;
		}
	}
	
	public ArrayList<Realisation> getActu() {
		ArrayList<Realisation> r = new ArrayList<>();
		for(int i = 0; i < liste.size();i++) {
			for (Realisation rea : liste.get(i).getJoueur().getRealisation()) {
					r.add(rea);
			}
		}
		return r;
	}
	
	public void tourSemaine(Couleur c) {
		ArrayList<Realisation> l = getActu();
		for(int i = 0; i < liste.size(); i++) {
			int gravite;
			for (Realisation rea : l) {
				gravite = rea.getTache().getAlea(c).getGravite();
				if (rea.getTache().getAlea(c).getType() == TypeAlea.DELAI) {
					rea.getTache().setDuree(rea.getTache().getAlea(c).getGravite());
				}
				if (rea.getTache().getAlea(c).getType() == TypeAlea.COUT) {
					liste.get(i).getJoueur().depense(gravite);
				}
				if (rea.getTache().getAlea(c).getType() == (TypeAlea.QUAL)) {
					liste.get(i).getJoueur().baisseQualite(gravite);
				}
			}
		}
	}
	
}
