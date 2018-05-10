package description;

import java.util.Collection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import tours.Tour;
import tours.TourJalon;
import tours.TourQuizz;
import tours.TourSemaine;

public class Description {
	
	private LinkedList<Tacheclass> tabTache;
	private HashMap<String, String[]> relation;
	private Tour[] sequence;
	public Description() {
		tabTache =new LinkedList<>();
		
		relation= new HashMap<String, String[]>();
		Aleas[] a ={(new Aleas(TypeAlea.DELAI, 1,Couleur.ROUGE)),
				(new Aleas(TypeAlea.DELAI, 2,Couleur.JAUNE)),
				(new Aleas(TypeAlea.COUT, 1,Couleur.VERT))};
		tabTache.add(new Tacheclass(10,a,"reflechir", 0,2,"1"));
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
		relation.put("2", new String[] {"5","7"});
		relation.put("3", new String[] {"5","7"});
		relation.put("4", new String[] {"5","7"});
		relation.put("5", new String[] {"6"});
		relation.put("6", new String[] {"8"});
		relation.put("7", new String[] {"8"});
		tabTache.add(new Tacheclass(20,tache2,"dire", 2,5,"2"));
		tabTache.add(new Tacheclass(10,tache3,"ecouter", 2,4,"3"));
		tabTache.add(new Tacheclass(10,tache4,"faire", 2,4,"4"));
		tabTache.add(new Tacheclass(10,tache5,"demander", 5,6,"5"));
		tabTache.add(new Tacheclass(10,tache6,"controler", 6,9,"6"));
		tabTache.add(new Tacheclass(20,tache7,"planifier", 5,8,"7"));
		tabTache.add(new Tacheclass(10,tache8,"presenter", 9,11,"8"));
		
		// la sequence a suivre chaque tour de jeu commence en checkant a quel type de tours on a à faire!! !
		sequence[0]= new TourJalon("Jalon", 0, new Tacheclass[] {
				tabTache.get(0), tabTache.get(1),tabTache.get(2),tabTache.get(3),tabTache.get(4)}); 
		sequence[1]=new TourSemaine("Semaine", 1, new Tacheclass[] {tabTache.get(0)});	
		sequence[2]=new TourSemaine("Semaine", 2, new Tacheclass[] {tabTache.get(1)});	
		sequence[3]=new TourSemaine("Semaine", 3, new Tacheclass[] {tabTache.get(2)});	
		sequence[4]=new TourSemaine("Semaine", 4, new Tacheclass[] {tabTache.get(3)});	
		sequence[5]=new TourQuizz("Quizz", 5, null);	
		sequence[6]=new TourJalon("Jalon", 6, new Tacheclass[] {tabTache.get(4),tabTache.get(5),tabTache.get(6),tabTache.get(7)}); 
		sequence[7]=new TourSemaine("Semaine", 7, new Tacheclass[] {tabTache.get(4)});	
		sequence[8]=new TourSemaine("Semaine", 8, new Tacheclass[] {tabTache.get(5)});	
		sequence[9]=new TourSemaine("Semaine", 9, new Tacheclass[] {tabTache.get(6)});	
		sequence[10]=new TourQuizz("Quizz", 10, null);
		sequence[11]=new TourSemaine("Semaine", 11, new Tacheclass[] {tabTache.get(7)});	
		sequence[12]= new TourSemaine("FINAL", 12, null);
	}
	
	public Tache getTacheById(String id) {
		for(Tacheclass ta : tabTache) {
			if(id.equalsIgnoreCase(ta.getId())) {
				return ta;
			}
		}
		return null;
	}
	protected HashMap<String, String[]> getRelation(){
		
		return relation;
	}
	public final Tache getDebut() {
		return this.tabTache.getFirst();
	}
	
	public final Tache getFin() {
		return this.tabTache.getLast();
	}
	
	public Couleur getRandom() {
		return Couleur.tirage();
	}
	
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
	
	public ArrayList<Tacheclass> getTaches() {
		ArrayList<Tacheclass> tab = new ArrayList<>();
		for(Tacheclass t : this.tabTache) {
			tab.add(t);
		}
		return tab;
	}
}
