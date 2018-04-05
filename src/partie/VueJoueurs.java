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
	private boolean[] protection;
	
	public VueJoueurs(DonneesJoueurs j) {
		this.joueur = j;
		this.nom = joueur.getNom();
		this.caisse = 300;
		this.qualite = joueur.getQualite();
	}

	@Override
	public void FinDuTour() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getCaisse() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCurrent(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getDebutId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Description getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getDuree(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Etat getEtat(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFinId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumeroTour() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getQualite() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setAcceleration(String id, boolean active) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setProtection(String id, Couleur couleur, boolean active) {
		// TODO Auto-generated method stub
		
	}

}
