package partie;

import java.util.ArrayList;

import description.Couleur;
import description.Description;
import description.Tache;
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
		return 0;
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
	public void setProtection(String id, Couleur couleur) {
		this.joueur.getRealisation(id).setProtection(couleur);
		
	}

	public DonneesJoueurs getJoueur() {
		return joueur;
	}
	
	public boolean isImminent(Realisation r) {
		ArrayList<Tache> a= new ArrayList<>();
		int b=0;
		for(Tache c : a) {
			if(this.joueur.getRealisation(c.getId()).getEtat().equals(Etat.TERMINEE)) {
				b++;
			}
		}
		return b==a.size();
	}
	

	public void tourSemaine(Couleur c, String id) {
		Realisation rea = joueur.getRealisation(id);
		if (rea.getEtat() == Etat.IMMINENT) {
		 int gravite = rea.getTache().getAlea(c).getGravite();
			if (rea.getTache().getAlea(c).getType() == TypeAlea.DELAI && !rea.isProtec(c)) {
				 joueur.getRealisation(id).getTache().setDuree(rea.getTache().getDureeInitiale() + gravite);
			}
			if (rea.getTache().getAlea(c).getType() == TypeAlea.COUT && !rea.isProtec(c)) {
				this.joueur.depense(gravite * 10);
			}
			if (rea.getTache().getAlea(c).getType() == (TypeAlea.QUAL) && !rea.isProtec(c)) {
				this.joueur.baisseQualite(gravite);
			}
		} else {
			rea.getTache().avancer();
		}
	}
	
}
