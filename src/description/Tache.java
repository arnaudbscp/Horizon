package description;

import java.util.Collection;

/**
 * 
 * @author bascopa
 *<p>Représente une tâche telle que définie par la structure du jeu. On n'inclut pas dans cette classe les données qui dépendent des équipes (comme par exemple la durée réelle de la tâche modifiée par les aleas). La liste complète des tâches et leur initialisation est de la responsabilité de la classe Description.</p>
 */

public interface Tache {
	/**
	 * 
	 * @return Le cout de l'accélération en euros.
	 */
	public int coutAcceleration();
	/**
	 * 
	 * @param couleur
	 * @return Fournit l'alea associé à une couleur pour la tâche courante.
	 */
	public Alea getAlea(Couleur couleur);
	/**
	 * 
	 * @return Fournit la description de la tâche (exemple: "Réaliser la promotion du produit").
	 */
	public String getDescription();
	/**
	 * 
	 * @return Fournit la durée initiale de la tâche.
	 */
	public int getDureeInitiale();
	/**
	 * 
	 * @return Fournit la durée maximale de la tâche.
	 */
	public int getDureeMax();
	/**
	 * 
	 * @return Donne l'identifiant de la tâche.
	 */
	public String getId();
	/**
	 * 
	 * @return Fournit la liste des tâches précédentes.
	 */
	public Collection<Tache> getPredecesseurs();
	/**
	 * 
	 * @return Fournit la lliste des tâches suivantes.
	 */
	public Collection<Tache> getSuccesseurs();
	/**
	 * 
	 * @return l'avancement
	 */
	public int getAvancement();
	public void avancer();
	public void setDuree(int i);


}
