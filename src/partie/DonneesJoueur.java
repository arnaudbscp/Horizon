package partie;

import strategie.Strategie;

public interface DonneesJoueur {
	
	
	public void actualisation(int temps);
	public void baisseQualite(int delta);
	public void depense(int delta);
	public int getCaisse();
	//public graphe.Graphe getGraphe();
	public  String getNom();
	public int  getQualite();
	//public Realisation getRealisation();
	public Strategie getStrategie();
	public void uneSemaine();
	
	
	
	
	
	
}
