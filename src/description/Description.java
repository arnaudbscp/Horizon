package description;

import java.util.Collection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Description {
	
	private LinkedList<Tacheclass> tabTache;
	private HashMap<String, String[]> relation;
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
