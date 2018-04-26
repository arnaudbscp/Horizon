package partie;

import description.Couleur;
import description.Description;

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
	public void setProtection(String id, Couleur couleur, boolean ok) {
		this.joueur.getRealisation(id).setProtection(couleur, ok);
		
	}

	public DonneesJoueurs getJoueur() {
		return joueur;
	}

}
