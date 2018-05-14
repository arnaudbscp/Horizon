package strategie;

import description.Couleur;
import description.Description;
import description.Tache;
import description.Tacheclass;
import partie.DonneesJoueurs;
import partie.Partie;
import partie.VueJoueur;
import partie.VueJoueurs;

public class IHMTexte implements Strategie {

	public void jouerEtape(VueJoueur vue) {
		System.out.println("Nom: " + vue.getNom() + "/// Caisse: " + vue.getCaisse() + "/// Qualit�: " + vue.getQualite() 
		+ "///ID Premi�re t�che: " + vue.getDebutId() + "///ID Derni�re t�che: " + vue.getFinId());
		System.out.println(vue.getDescription());
	}


	public void jouerJalon(VueJoueur vue) {
		
	}

	public void jouerTest(VueJoueur vue) {
		
	}
	
	public static void afficheTache(Tache t) {
		System.out.println(t.getId() + " | " + t.getDescription()); 
		System.out.println("Durée initiale: " + t.getDureeInitiale() + " - Durée max: " + t.getDureeMax());
		System.out.println(t.getAlea(Couleur.ROUGE).getType() + " - " + t.getAlea(Couleur.JAUNE).getType() + " - " + t.getAlea(Couleur.VERT).getType());
		System.out.println("------------------------------------");
	}
	
	public static void main(String[] args) {
		Description d = new Description(); 
		Partie partie = new Partie(d, "SAMUEL");
		DonneesJoueurs donneesSam = partie.getVueJoueur("SAMUEL").getJoueur();
		VueJoueurs vueSam = partie.getVueJoueur("SAMUEL");
		for(Tache t : d.getTaches()) {
		afficheTache(t);
		}
	}
}
