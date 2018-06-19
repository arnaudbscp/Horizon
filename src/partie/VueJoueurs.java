package partie;

import java.util.ArrayList;

import description.Couleur;
import description.Description;
import description.Tache;
import description.Tacheclass;
import description.TypeAlea;

public class VueJoueurs implements VueJoueur{
	private DonneesJoueurs joueur;
	private Description description;
	private int numeroT; // mettre numero tour dans partie / faire un enum pour numéro tour
	
	public VueJoueurs(String nom) {
		joueur = new DonneesJoueurs(nom);
		description = new Description();
		
	}

	@Override
	public void FinDuTour() {
		this.numeroT++;
	}

	@Override
	public int getCaisse() {
		return this.joueur.getCaisse();
	}

	@Override
	public int getCurrent(String id) {
		// TODO Auto-generated method stub
		return this.description.getTacheById(id).getAvancement() ;
	}

	@Override
	public String getDebutId() {
		return this.description.getDebut().getId();
	}

	@Override
	public Description getDescription() {
		return this.description;
	}

	@Override
	public int getDuree(String id) {
		return getDescription().getTacheById(id).getDureeInitiale();
	}

	@Override
	public Etat getEtat(String id) {
		return this.joueur.getRealisation(id).getEtat();
	}

	@Override
	public String getFinId() {
		return this.description.getFin().getId();
	}

	@Override
	public String getNom() {
		return this.joueur.getNom();
	}

	@Override
	public int getNumeroTour() {
		return this.numeroT;
	}

	@Override
	public int getQualite() {
		return this.joueur.getQualite();
	}

	@Override
	public void setAcceleration(String id, boolean ok) {
		if(ok) {
			this.joueur.getRealisation(id).setAcceleration(ok);
		}
	}

	@Override
	public void setProtection(String id, Couleur couleur, boolean ok) {
		this.joueur.getRealisation(id).setProtection(couleur, ok);
		
	}

	public DonneesJoueurs getJoueur() {
		return joueur;
	}
	
	public boolean isImminent(Realisation r) {
		ArrayList<Tache> a= new ArrayList<>();
		int b=0;
		a= (ArrayList<Tache>) r.getTache().getPredecesseurs();
		for(Tache c : a) {
			if(this.joueur.getRealisation(c.getId()).getEtat().equals(Etat.TERMINEE)) {
				b++;
			}
		}
		return b==a.size();
	}
	
	public ArrayList<Realisation> getSemainesaAvancer() {
		ArrayList<Realisation> semaines = new ArrayList<>();
		
		for(Realisation r: this.joueur.getRealisation()) {
			if(r.getEtat() == Etat.EN_COURS || r.getEtat() == Etat.IMMINENT) {
			semaines.add(r);}
			
		}		
		return semaines;
	}
	
	
	public void tourSemaine(Couleur c, Realisation rea) {
		if (rea.getEtat() == Etat.IMMINENT ) {
		 int gravite = rea.getTache().getAlea(c).getGravite();
			if (rea.getTache().getAlea(c).getType() == TypeAlea.DELAI && !rea.isProtec(c)) {
				rea.getTache().setDuree(rea.getTache().getDureeInitiale() + gravite);
			}
			if (rea.getTache().getAlea(c).getType() == TypeAlea.COUT && !rea.isProtec(c)) {
				this.joueur.depense(gravite * 10);
			}
			if (rea.getTache().getAlea(c).getType() == (TypeAlea.QUAL) && !rea.isProtec(c)) {
				this.joueur.baisseQualite(gravite);
			}
		}
		rea.setEtat(Etat.EN_COURS); // 
		
		rea.avancer();
		if(rea.getEtat().equals(Etat.TERMINEE)) {
			for(Tache a : rea.getTache().getSuccesseurs()) {
				if(isImminent(joueur.getRealisation(a.getId()))) {
					joueur.getRealisation(a.getId()).setEtat(Etat.IMMINENT);
				}
			}
		}
	}
	
}
