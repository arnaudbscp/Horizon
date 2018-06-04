package partie;

import description.Couleur;
import description.Description;

public interface VueJoueur {
	public void FinDuTour();
	public int getCaisse();
	public int getCurrent(String id);
	public String getDebutId();
	public Description getDescription();
	public int getDuree(String id);
	public Etat getEtat(String id);
	public String getFinId();
	public String getNom();
	public int getNumeroTour();
	public int getQualite();
	public void setAcceleration(String id, boolean active);
	public void setProtection(String id, Couleur couleur, boolean active);
}
