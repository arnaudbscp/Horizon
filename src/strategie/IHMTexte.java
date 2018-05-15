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
		//Affichage ID + Intitulé
		System.out.println(t.getId() + " | " + t.getDescription()); 
		//Affichage des ronds pour les semaines
		for(int i = 0; i<t.getDureeInitiale(); i++) {
			System.out.print(" ○ ");
		} for(int j = 0; j<t.getDureeMax()-t.getDureeInitiale(); j++) {
			System.out.print(" ● ");
		}
		System.out.print("\n");
		//Affichage des aléas sous forme de lettres
		System.out.print(" ");
		for(int k = 0; k < t.getAlea(Couleur.ROUGE).getGravite(); k++) {
			System.out.print("D");
		} 
		System.out.print(" / ");
		for(int l = 0; l < t.getAlea(Couleur.JAUNE).getGravite(); l++) {
			System.out.print("C");
		} 
		System.out.print(" / ");
		for(int m = 0; m < t.getAlea(Couleur.VERT).getGravite(); m++) {
			System.out.print("Q");
		}
	} 
	
	public static void main(String[] args) {
		Description d = new Description(); 
		Partie partie = new Partie(d, "SAMUEL");
		DonneesJoueurs donneesSam = partie.getVueJoueur("SAMUEL").getJoueur();
		VueJoueurs vueSam = partie.getVueJoueur("SAMUEL");
		
		afficheTache(d.getDebut());
		
		for(Tour t : d.getSequence()) {
			switch(t.getType()) {
			case "Semaine": 
				partie.tourSemaine(t.getTaches()[0].getId());
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
