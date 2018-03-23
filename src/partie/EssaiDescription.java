package partie;

import description.Alea;
import description.Couleur;
import description.Description;
import description.Tache;
import description.Tacheclass;
public class EssaiDescription {

	public static void main(String[] args) {
		Description d= new Description();
		Tache a= d.getTacheById("1");
		System.out.println(a.getId());
		System.out.println(a.getDescription());
		Alea b =a.getAlea(Couleur.JAUNE);
		System.out.println(b);
		
	}
}
