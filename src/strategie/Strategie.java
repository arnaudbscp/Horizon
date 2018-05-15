package strategie;

import partie.VueJoueur;

/**
 * <p>
 * Cette interface décrit les méthodes que peut employer le moteur du jeu pour obtenir les décisions du joueur. Les classes concrètes implémentent cette interface pourront représenter
<ul>
    <li>un joueur simple avec une IHM texte,</li>
    <li>un joueur avec une IHM graphique,</li>
    <li>une intelligence artificielle,</li>
    <li>un joueur sur le réseau,</li>
    <li>etc.</li>
</ul>
La méthode utilisée détermine le contexte. Les données à obtenir et les ordre transmis le seront uniquement grace aux l'objetq Equipe et VueJoueur passés en paramètres. La méthode peut demander l'exécution d'un nombre quelconque d'ordres et peut inclure l'ordre FinDuTour(). Si cet ordre n'est pas appelé, la méthode jouer sera à nouveau appelée par le moteur du jeu, permettant le réaffichage et la saisie de nouveau ordres.
 * </p>
 * @author bascopa
 *
 */

public interface Strategie {
	void jouerEtape(VueJoueur vue); 
	void jouerJalon(VueJoueur vue); 
	void jouerTest(VueJoueur vue);
}
