package strategie;

import partie.VueJoueur;

public interface Strategie {
	void jouerEtape(VueJoueur vue); 
	void jouerJalon(VueJoueur vue); 
	void jouerTest(VueJoueur vue);
}
