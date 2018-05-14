package strategie;

import description.Couleur;
import description.Description;
import description.Tache;
import description.Tacheclass;
import partie.DonneesJoueurs;
import partie.Partie;
import partie.VueJoueur;
import partie.VueJoueurs;
import tours.Tour;

public class IHMTexte {
	
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
		
		for(Tour t : d.getSequence()) {
			switch(t.getType()) {
			case "Semaine": 
				partie.jouerEtape(vueSam);
				break;
			case "Quizz": 
				//Pas pour l'étape 2 
				break;
			case "Jalon": 
				//Pas pour l'étape 2 
				break;
			case "FINAL": 
				
			}
		}
	}
}
