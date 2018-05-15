package description;

/**
 * <p>Représente un des aleas envisagé durant le déroulement du jeu.</p>
 * 
 * @author bascopa
 *
 */

public interface Alea {
	
	/**
	 * Indique la gravité d'un alea. Il s'agit d'un entier. L'unité de mesure dépend du type de l'alea (getType().
	 * @see getType()
	 * @return Le niveau de gravité de l'alea.
	 */
	public int getGravite();
	/**
	 * Indique le type d'impact de l'alea.
	 * @return Le type de l'alea.
	 */
	public TypeAlea getType();

}
