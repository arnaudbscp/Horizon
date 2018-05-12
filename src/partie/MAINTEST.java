package partie;

import description.Description;
import strategie.Strategie;

public class MAINTEST implements Strategie {

	public void jouerEtape(VueJoueur vue) {
		System.out.println("Nom: " + vue.getNom() + "/// Caisse: " + vue.getCaisse() + "/// Qualité: " + vue.getQualite() 
		+ "///ID Première tâche: " + vue.getDebutId() + "///ID Dernière tâche: " + vue.getFinId());
		System.out.println(vue.getDescription());
	}


	public void jouerJalon(VueJoueur vue) {
		
	}

	public void jouerTest(VueJoueur vue) {
		
	}
	
	public static void main(String[] args) {
		Description d = new Description(); 
		Partie partie = new Partie(d, "SAMUEL");
		DonneesJoueurs donneesSam = partie.getVueJoueur("SAMUEL").getJoueur();
		VueJoueurs vueSam = partie.getVueJoueur("SAMUEL");
		
	}
}
