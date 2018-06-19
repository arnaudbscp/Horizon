package description;

import java.util.Collection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import tours.Tour;
import tours.TourJalon;
import tours.TourQuizz;
import tours.TourAlea;

/**
 * 
 * @author bascopa
 * <p> Objet qui permet d'obtenir la structure du jeu. Au moyen des éléments suivants:

    La liste des tâches,
    la liste des aleas envisagés,
    les relations entre tâches (i.e. le dessin du graphe).
	</p>
 */

public class Description {
	
	private LinkedList<Tacheclass> tabTache;
	private HashMap<String, String[]> relation;
	private Tour[] sequence;
	public Description() {
		tabTache =new LinkedList<>();
		
		relation= new HashMap<String, String[]>();
		Aleas[] tache1 ={(new Aleas(TypeAlea.DELAI, 1,Couleur.ROUGE)),
				(new Aleas(TypeAlea.DELAI, 2,Couleur.JAUNE)),
				(new Aleas(TypeAlea.COUT, 1,Couleur.VERT))};
		Aleas[] tache2 ={(new Aleas(TypeAlea.DELAI, 1,Couleur.ROUGE)),
				(new Aleas(TypeAlea.DELAI, 1,Couleur.JAUNE)),
				(new Aleas(TypeAlea.COUT, 2,Couleur.VERT))};
		Aleas[] tache3 ={(new Aleas(TypeAlea.DELAI, 2,Couleur.ROUGE)),
				(new Aleas(TypeAlea.COUT, 1,Couleur.JAUNE)),
				(new Aleas(TypeAlea.DELAI, 2,Couleur.VERT))};
		Aleas[] tache4 ={(new Aleas(TypeAlea.COUT, 1,Couleur.ROUGE)),
				(new Aleas(TypeAlea.DELAI, 2,Couleur.JAUNE)),
				(new Aleas(TypeAlea.COUT, 1,Couleur.VERT))};
		Aleas[] tache5 ={(new Aleas(TypeAlea.DELAI, 1,Couleur.ROUGE)),
				(new Aleas(TypeAlea.DELAI, 3,Couleur.JAUNE)),
				(new Aleas(TypeAlea.COUT, 2,Couleur.VERT))};
		Aleas[] tache6 ={(new Aleas(TypeAlea.DELAI, 1,Couleur.ROUGE)),
				(new Aleas(TypeAlea.DELAI, 1,Couleur.JAUNE)),
				(new Aleas(TypeAlea.QUAL, 1,Couleur.VERT))};
		Aleas[] tache7 ={(new Aleas(TypeAlea.DELAI, 3,Couleur.ROUGE)),
				(new Aleas(TypeAlea.DELAI, 1,Couleur.JAUNE)),
				(new Aleas(TypeAlea.DELAI, 1,Couleur.VERT))};
		Aleas[] tache8 ={(new Aleas(TypeAlea.DELAI, 1,Couleur.ROUGE)),
				(new Aleas(TypeAlea.COUT, 2,Couleur.JAUNE)),
				(new Aleas(TypeAlea.QUAL, 2,Couleur.VERT))};
		relation.put("1", new String[] {"2","3","4"});
		relation.put("2", new String[] {"5","6", "7"});
		relation.put("3", new String[] {"5","7"});
		relation.put("4", new String[] {"5","7"});
		relation.put("5", new String[] {"8"});
		relation.put("6", new String[] {"8"});
		relation.put("7", new String[] {"8"});
		tabTache.add(new Tacheclass(10, tache1, "Réfléchir", 2, 4, "1")); 
		tabTache.add(new Tacheclass(20,tache2,"Dire", 3,4,"2"));
		tabTache.add(new Tacheclass(10,tache3,"Ecouter", 2,4,"3"));
		tabTache.add(new Tacheclass(10,tache4,"Faire", 2,4,"4"));
		tabTache.add(new Tacheclass(10,tache5,"Demander", 1,4,"5"));
		tabTache.add(new Tacheclass(10,tache6,"Contrôler", 3,4,"6"));
		tabTache.add(new Tacheclass(20,tache7,"Planifier", 3,6,"7"));
		tabTache.add(new Tacheclass(10,tache8,"Présenter", 2,4,"8"));
		
		// la sequence a suivre chaque tour de jeu commence en checkant a quel type de tours on a à faire!! !
		sequence = new Tour[] {
				new TourJalon("Jalon", 0, new Tacheclass[] {
						tabTache.get(0), tabTache.get(1),tabTache.get(2),tabTache.get(3),tabTache.get(4)}),
				new TourAlea("Semaine", 1, new Tacheclass[] {tabTache.get(0)}),
				new TourAlea("Semaine", 2, new Tacheclass[] {tabTache.get(1)}),	
				new TourAlea("Semaine", 3, new Tacheclass[] {tabTache.get(2)}),	
				new TourAlea("Semaine", 4, new Tacheclass[] {tabTache.get(3)}),	
				new TourQuizz("Quizz", 5, null),
				new TourJalon("Jalon", 6, new Tacheclass[] {tabTache.get(4),tabTache.get(5),tabTache.get(6),tabTache.get(7)}),
				new TourAlea("Semaine", 7, new Tacheclass[] {tabTache.get(4)}),
				new TourAlea("Semaine", 8, new Tacheclass[] {tabTache.get(5)}),	
				new TourAlea("Semaine", 9, new Tacheclass[] {tabTache.get(6)}),	
				new TourQuizz("Quizz", 10, null),
				new TourAlea("Semaine", 11, new Tacheclass[] {tabTache.get(7)}),
				new TourAlea("FINAL", 12, null)
		};
	
	}
	
	/**
	 * <p>Fournit la tâche désignée par un identifiant donné</p>
	 * @param id
	 * @return la tâche
	 */
	public Tache getTacheById(String id) {
		for(Tacheclass ta : tabTache) {
			if(id.equalsIgnoreCase(ta.getId())) {
				return ta;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @return la relation
	 */
	protected HashMap<String, String[]> getRelation(){
		
		return relation;
	}
	
	/**
	 * Fournit la tâche initiale (elle est unique).
	 * @return la tache
	 */
	public final Tache getDebut() {
		return this.tabTache.getFirst();
	}
	
	/**
	 * Fournit la tâche terminale (elle est uniquye).
	 * @return la tache de fin
	 */
	public final Tache getFin() {
		return this.tabTache.getLast();
	}
	
	/**
	 * Fournit une couleur d'alea au hasard (cf Couleur.tirage()).
	 * @return Couleur
	 */
	public Couleur getRandom() {
		return Couleur.tirage();
	}
	
	/**
	 * 
	 * @return le chemin critique
	 */
	public Collection<Tache> getCheminCritique() {
		ArrayList<Tache> liste = new ArrayList<>();
		Tache tache1 = this.getDebut(); 
		liste.add(tache1);
		Collection<Tache> successeurs = new ArrayList<>();
		successeurs = tache1.getSuccesseurs();
		HashMap<String, Collection<Tache>> successeurs2 = new HashMap<>();
		for(Tache i : successeurs) {
			successeurs2.put(i.getId(), i.getSuccesseurs());
		}
		//SUCCESSEURS 2 = <"2", "5, 7">, <"3", "5, 7">, <"4", "5, 7"> 
		for(HashMap.Entry<String,Collection<Tache>> e : successeurs2.entrySet()) {
			String[] tab = (String[]) e.getValue().toArray();	
		}
		return successeurs;
	}
	
	/**
	 * 
	 * @return Retourne l'ensemble des taches.
	 */
	public ArrayList<Tacheclass> getTaches() {
		ArrayList<Tacheclass> tab = new ArrayList<>();
		for(Tacheclass t : this.tabTache) {
			tab.add(t);
		}
		return tab;
	}
	
	/**
	 * 
	 * @return la séquence
	 */
	public Tour[] getSequence() {
		return sequence;
	}
}
