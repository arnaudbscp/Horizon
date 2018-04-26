package partie;

import java.util.ArrayList;

import strategie.Strategie;

public interface DonneesJoueur {
	
	
	public void actualisation(int temps);
	public void baisseQualite(int delta);
	public void depense(int delta);
	public int getCaisse();
	public  String getNom();
	public int  getQualite();
	public ArrayList<Realisation> getRealisation();
	public Strategie getStrategie();
	
}
