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
		return this.caisse;
	}

	@Override
	public int getCurrent(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getDebutId() {
		return this.debutId;
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
		
		return null;
	}

	@Override
	public String getFinId() {
		return this.finId;
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
		return this.qualite;
	}

	@Override
	public void setAcceleration(String id, boolean active) {
		if(active) {
			this.duree = getDuree(id)-1;
		}
	}

	@Override
	public void setProtection(String id, Couleur couleur, boolean active) {
		// TODO Auto-generated method stub
		
	}

	public DonneesJoueurs getJoueur() {
		return joueur;
	}

}
