package partie;

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
		System.out.println(v.getCaisse());
		
	}
}
