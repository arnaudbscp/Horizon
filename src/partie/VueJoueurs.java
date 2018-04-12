package partie;

import description.Couleur;
import description.Description;

public class VueJoueurs implements VueJoueur{
	private DonneesJoueurs joueur;
	private String nom;
	private int caisse;
	private int qualite;
	private String debutId;
	private String finId;
	private Description description;
	private int duree;
	private int current;
	private Etat etat;
	private int numeroT; // mettre numero tour dans partie / faire un enum pour numéro tour
	private boolean active;
	
	public VueJoueurs(DonneesJoueurs j) {
		this.joueur = j;
		this.nom = joueur.getNom();
		this.caisse = 300;
		this.qualite = joueur.getQualite();
		this.numeroT = 0;
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
		return this.nom;
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

}
