package partie;

import static org.junit.Assert.assertEquals;

import description.Couleur;
import description.Description;

public class tetst {

	public static void main(String[] args) {
		
		final String NOM_JOUEUR = "NOM_JOUEUR";
		Description description;
		
		DonneesJoueur donneesJoueur;
		description = new Description();
		Partie partie= new Partie(description,"NOM_JOUEUR");
		donneesJoueur =  partie.getDonneesJoueur(NOM_JOUEUR);
		VueJoueur v =partie.getVueJoueur(NOM_JOUEUR);
		if(v == null) {
			System.out.println("1");
		}
		System.out.println(partie.getTour());
		partie.passerTour();
		System.out.println(partie.getTour());
		System.out.println(v.getCaisse());
		partie.tourSemaine(Couleur.VERT);
		System.out.println(donneesJoueur.getCaisse());
		assertEquals(290, donneesJoueur.getCaisse());
		

	}
}
