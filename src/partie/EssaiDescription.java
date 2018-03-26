package partie;

import java.util.ArrayList;
import java.util.Collection;

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
		System.out.println(b.getGravite());
		System.out.println(b.getType());
		Alea c = a.getAlea(Couleur.VERT);
		System.out.println(c.getGravite());
		System.out.println(c.getType());
		Tache ab=d.getTacheById("8");
		System.out.println(ab.getId());
		System.out.println(ab.getDescription());
		Alea de = ab.getAlea(Couleur.VERT);
		System.out.println(de.getGravite());
		System.out.println(de.getType());
		de = ab.getAlea(d.getRandom());
		System.out.println(de.getGravite());
		System.out.println(de.getType());
		Tache be = d.getDebut();
		System.out.println(be.getDescription()+"  "+be.getId());
		Tache det =d.getFin();
		System.out.println(det.getDescription()+"  "+det.getId());
		
		
		Collection<Tache> abbt = new ArrayList<Tache>();
		abbt = be.getSuccesseurs();
		for(Tache abbb: abbt) {
			System.out.println(abbb.getDescription());
		}
		Collection<Tache> abbtt = new ArrayList<Tache>();
		abbtt = be.getPredecesseurs();
		for(Tache abbb: abbtt) {
			System.out.println(" /"+abbb.getDescription());
		}
		
	}
}
