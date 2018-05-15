package partie;
/**
 * <p>Les différents états que peut prendre une réalisation. ceci concerne uniquement l'état d'avancement de la tâche. Le fait que l'alea ait - ou non - été déterminé pour la tâche n'influe pas sur l'état décrit ici.</p>
 * 
 * @author bascopa
 *
 */
public enum Etat {
	/**
	 * Le réalisation est en cours.
	 */
	EN_COURS('E'),
	/**
	 * La réalisation n'est pas encore entamée mais toutes les conditions sont respectées pour un démarrage immédiat.
	 */
	IMMINENT('I'),
	/**
	 * La réalisation n'est pas encore entamée.
	 */
	NON_ENTAMEE('N'),
	/**
	 * La réalisation est terminée.
	 */
	TERMINEE('T');
	
	private char c; 
	
	private Etat(char c) {
		this.c = c;
	}
	
	public char getChar() {
		return this.c;
	}
}
