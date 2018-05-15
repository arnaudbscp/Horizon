package partie;

import java.util.ArrayList;
import java.util.Collection;

import description.Couleur;
import description.Description;
import description.TypeAlea;
import strategie.Strategie;
import description.Tache;
import description.Tacheclass;

public class Partie implements Strategie {
	
	private Description description; 
	private int tour;
	private String joueur; 
	private ArrayList<VueJoueurs> liste;
		
	public Partie(Description d, String j) {
		liste = new ArrayList<>();
		this.description = d; 
		this.joueur = j;
		liste.add(new VueJoueurs(j));
		for(VueJoueurs a: liste) {
			a.getJoueur().getRealisation("1").setEtat(Etat.IMMINENT);
			
		}
	}
	
	public DonneesJoueurs getDonneesJoueur(String joueur) {
		for(VueJoueurs j : liste) {
			if(j.getNom().equalsIgnoreCase(joueur)) {
				return  j.getJoueur(); 
			}
		}
		return null;
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
			if(r.getEtat()==Etat.EN_COURS) {
				r.getTache().avancer();
				if(r.getTache().getDureeInitiale()==r.getTache().getAvancement()) {
					r.setEtat(Etat.TERMINEE);
					setImminent(r);
				}
			}
				
		}
		tour++;
	}
	public int getTour() {
		return tour;
	}
	private void setImminent(Realisation r) {
		// TODO Auto-generated method stub
		Collection<Tache> a = r.getTache().getSuccesseurs();
		String[] b = null;
		int c=0;
		for(Tache t:a) {
			b[c++]=t.getId();
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
				if(rea.getEtat()==Etat.IMMINENT) {
					gravite = rea.getTache().getAlea(c).getGravite();
					if (rea.getTache().getAlea(c).getType() == TypeAlea.DELAI) {
						rea.getTache().setDuree(rea.getTache().getDureeInitiale()+gravite);
					}
					if (rea.getTache().getAlea(c).getType() == TypeAlea.COUT) {
						liste.get(i).getJoueur().depense(gravite*10);
					}
					if (rea.getTache().getAlea(c).getType() == (TypeAlea.QUAL)) {
						liste.get(i).getJoueur().baisseQualite(gravite);
					}
				}
			}
		}
	}
	
	public void jouerEtape(VueJoueur vue) {
		
	}


	public void jouerJalon(VueJoueur vue) {
		
	}

	public void jouerTest(VueJoueur vue) {
		
	}
}
